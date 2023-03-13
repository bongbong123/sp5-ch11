package com.example.sp5ch11.service;

import com.example.sp5ch11.dto.Member;
import com.example.sp5ch11.exception.MemberNotFoundException;
import com.example.sp5ch11.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Component
@RequiredArgsConstructor
public class ChangePasswordService {
    private final MemberRepository memberRepository;

    @Transactional
    public void changePassword(String email, String oldPwd, String newPwd) {
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        member.changePassword(oldPwd, newPwd);
//        memberRepository.save(member);
    }

}
