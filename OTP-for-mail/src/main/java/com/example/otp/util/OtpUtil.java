package com.example.otp.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {
    public String GenerateOtp(){
        Random random=new Random();
        int randamNumber=random.nextInt(999999);
        String output=Integer.toString(randamNumber);
        while(output.length()<6){
            output="0"+output;
        }
        return output;
    }
}
