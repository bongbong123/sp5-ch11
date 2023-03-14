package com.example.sp5ch11.controller;

import com.example.sp5ch11.dto.AuthInfo;
import com.example.sp5ch11.dto.LoginCommand;
import com.example.sp5ch11.exception.WrongIdPasswordException;
import com.example.sp5ch11.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @GetMapping("/login")
    public String form(LoginCommand loginCommand,
                       @CookieValue(value = "REMEMBER", required = false) Cookie rCookie) {
        if (rCookie != null) {
            loginCommand.setEmail(rCookie.getValue());
            loginCommand.setRememberEmail(true);
        }
        return "login/loginForm";
    }

    @PostMapping("/login/submit")
    public String submit(@ModelAttribute LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response, Model model) {
        new LoginCommandValidator().validate(loginCommand, errors);
        if (errors.hasErrors()) {
            return "login/loginForm";
        }

        try {
            AuthInfo authInfo = authService.authenticate(
                    loginCommand.getEmail(),
                    loginCommand.getPassword()
            );

            session.setAttribute("authInfo", authInfo);
            model.addAttribute("authInfo", authInfo);

            Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
            rememberCookie.setPath("/");
            if (loginCommand.isRememberEmail()) {
                rememberCookie.setMaxAge(60 * 60 * 24 * 30);
            } else {
                rememberCookie.setMaxAge(0);
            }

            response.addCookie(rememberCookie);

            return "login/loginSuccess";
        } catch (WrongIdPasswordException e) {
            errors.reject("idPassrodNotMatching");
            return "login/loginForm";
        }
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

}
