package com.example.otp.service;

import com.example.otp.dto.LoginDto;
import com.example.otp.dto.RegisterDto;
import com.example.otp.entity.Users;
import com.example.otp.repository.UserRepository;
import com.example.otp.util.EmailUtil;
import com.example.otp.util.OtpUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UserRepository userRepository;
    public String register(RegisterDto registerDto) {
        String otp=otpUtil.GenerateOtp();
        try {
            emailUtil.sentOtpEmail(registerDto.getEmail(),otp);
        } catch (MessagingException e) {
            throw new RuntimeException("unable to sent otp please try again");
        }
        Users users=new Users();
        users.setFirstName(registerDto.getFirstName());
        users.setLastName(registerDto.getLastName());
        users.setEmail(registerDto.getEmail());
        users.setMobileNumber(registerDto.getMobileNumber());
        users.setOtp(otp);
        users.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(users);
        return "user Registration is successful";
    }

    public String verifyAccount(String email, String otp) {
        Users users= userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found with this email"+email));
        if (users.getOtp().equals(otp) && Duration.between(users.getOtpGeneratedTime(),LocalDateTime.now()).getSeconds()<(5*60))
        {
            users.setActive(true);
            userRepository.save(users);
            return "Otp verified you can login";
        }
        return "please re generate otp and try again";
    }

    public String regenerateOtp(String email) {
        Users users= userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found with this email"+email));
        String otp=otpUtil.GenerateOtp();
        try {
            emailUtil.sentOtpEmail(email,otp);
        } catch (MessagingException e) {
            throw new RuntimeException("unable to sent otp please try again");
        }
        users.setOtp(otp);
        users.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(users);
        return "Email send..... please verify account with in 1 minute";
    }
    public String login(LoginDto loginDto) {
        Users users= userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(()-> new RuntimeException("User not found with this email"+loginDto.getEmail()));
        if (!loginDto.getOtp().equals(users.getOtp())){
            return "Password is incorrect";
        }else if(!users.isActive()){
            return "Your account is not verified";
        }
        return "login Successful";
    }
}
