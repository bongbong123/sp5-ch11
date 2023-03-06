package com.example.sp5ch11.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String email;
    private String password;
    private String confirmPassword;
    private String name;
}
