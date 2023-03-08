package com.example.sp5ch11.controller;

import com.example.sp5ch11.dto.Member;
import com.example.sp5ch11.dto.RegisterRequest;
import com.example.sp5ch11.exception.DuplicateMemberException;
import com.example.sp5ch11.service.MemberRegistrerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistController {

    private final MemberRegistrerService memberRegistrerService;

    @GetMapping("/step1")
    public String handleStep1(){
        return "register/step1";
    }

    @PostMapping("/step2")
    public String handleStep2(
            @RequestParam(value = "agree", defaultValue = "false") Boolean agree) {
        if(!agree){
            return "register/step1";
        }

        return "register/step2";
    }

    @GetMapping("/step2")
    public String handleStep2Get() {
        return "redirect:step1";
    }

    @PostMapping("/step3")
    public String handleStep3(@ModelAttribute("fromData") RegisterRequest req) {
        try {
            memberRegistrerService.regist(req.getEmail(), req.getName(), req.getPassword());
            return "register/step3";
        } catch (DuplicateMemberException ex) {
            return "register/step2";
        }
    }
}
