package com.example.sp5ch11.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCommand {
    private String email;
    private String password;
    private boolean rememberEmail;
}
