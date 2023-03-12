package com.example.sp5ch11.controller;

import com.example.sp5ch11.dto.AuthInfo;
import com.example.sp5ch11.dto.LoginCommand;
import com.example.sp5ch11.exception.WrongIdPasswordException;
import com.example.sp5ch11.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @GetMapping
    public String form(LoginCommand loginCommand) {
        return "login/loginForm";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute LoginCommand loginCommand, Errors errors) {
        new LoginCommandValidator().validate(loginCommand, errors);
        if (errors.hasErrors()) {
            return "login/loginForm";
        }

        try {
            AuthInfo authInfo = authService.authenticate(
                    loginCommand.getEmail(),
                    loginCommand.getPassword()
            );
            return "login/loginSuccess";
        } catch (WrongIdPasswordException e) {
            errors.reject("idPassrodNotMatching");
            return "login/loginForm";
        }
    }

}
