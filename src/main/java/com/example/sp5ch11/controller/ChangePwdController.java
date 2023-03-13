package com.example.sp5ch11.controller;

import com.example.sp5ch11.dto.AuthInfo;
import com.example.sp5ch11.dto.ChangePwdCommand;
import com.example.sp5ch11.exception.WrongIdPasswordException;
import com.example.sp5ch11.service.ChangePasswordService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit/changePassword")
@RequiredArgsConstructor
public class ChangePwdController {

    private final ChangePasswordService changePasswordService;

    @GetMapping
    public String form(
            @ModelAttribute("command")ChangePwdCommand pwdCmd,
            HttpSession session) {
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        if (authInfo == null) {
            return "redirect:/login";
        }

        return "edit/changePwdForm";
    }

    @PostMapping
    public String submit(
            @ModelAttribute("command") ChangePwdCommand pwdCmd,
            Errors errors,
            HttpSession session) {

        new ChangePwdCommandValidator().validate(pwdCmd, errors);
        if (errors.hasErrors()) {
            return "edit/changePwdForm";
        }

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");

        try {
            changePasswordService.changePassword(
                    authInfo.getEmail(),
                    pwdCmd.getCurrentPassword(),
                    pwdCmd.getNewPassword());

            return "edit/changedPwd";
        } catch (WrongIdPasswordException e) {
            errors.rejectValue("currentPassword", "notMatching");
            return "edit/changePwdForm";
        }
    }
}
