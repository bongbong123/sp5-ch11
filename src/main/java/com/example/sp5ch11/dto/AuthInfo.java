package com.example.sp5ch11.dto;

import jakarta.persistence.SecondaryTable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthInfo {

    private Long id;
    private String email;
    private String name;

    public AuthInfo(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
