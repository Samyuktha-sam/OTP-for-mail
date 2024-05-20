package com.example.otp.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sentOtpEmail(String email,String otp) throws MessagingException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify otp");
        mimeMessageHelper.setText(otp+"""
                <div>
                <a href="http://localhost:8080/verify-account?email=%s&otp=%s" target="_blank">click link to verify</a>
                <div>
                """.formatted(email,otp),true);
        javaMailSender.send(mimeMessage);
    }
}
