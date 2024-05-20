package com.example.otp.controller;

import com.example.otp.dto.LoginDto;
import com.example.otp.dto.RegisterDto;
import com.example.otp.service.UserService;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(userService.register(registerDto), HttpStatus.OK);
    }

    @PutMapping("/verifyaccount")
    public ResponseEntity<String> verifyAccount(@RequestParam String email, @RequestParam String otp){
        return new ResponseEntity<>(userService.verifyAccount(email,otp),HttpStatus.OK);
    }

    @PutMapping("/regenerateOtp")
    public ResponseEntity<String>regenerateOtp(@RequestParam String email){
        return new ResponseEntity<>(userService.regenerateOtp(email),HttpStatus.OK);

    }

    @PutMapping("/login")
    public ResponseEntity<String>login(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(userService.login(loginDto),HttpStatus.OK);

    }
}
