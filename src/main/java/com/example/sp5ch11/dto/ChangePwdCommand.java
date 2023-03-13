package com.example.sp5ch11.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePwdCommand {

    private String currentPassword;
    private String newPassword;
}
