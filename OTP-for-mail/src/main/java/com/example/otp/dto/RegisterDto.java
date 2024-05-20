package com.example.otp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
}
