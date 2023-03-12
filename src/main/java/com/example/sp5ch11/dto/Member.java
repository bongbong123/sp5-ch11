package com.example.sp5ch11.dto;

import com.example.sp5ch11.exception.WrongIdPasswordException;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String email;
    private String mb_id;
    private String mb_pw;

    public boolean isPasswordEqualToConfirmPassword(String pwd, String confirmPwd){
        return pwd.equals(confirmPwd);
    }

    public void changePassword(String oldPwd, String newPwd){
        if(!mb_pw.equals(oldPwd)){
            throw new WrongIdPasswordException();
        }

        this.mb_pw = newPwd;
    }

    public static Member CreateMember(String email, String mb_id, String mb_pw){
        return Member.builder()
                .email(email)
                .mb_id(mb_id)
                .mb_pw(mb_pw)
                .build();
    }

    public boolean matchPassword(String password) {
        return this.mb_pw.equals(password);
    }
}