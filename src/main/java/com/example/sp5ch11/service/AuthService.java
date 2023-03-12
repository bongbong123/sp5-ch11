package com.example.sp5ch11.service;

import com.example.sp5ch11.dto.AuthInfo;
import com.example.sp5ch11.dto.Member;
import com.example.sp5ch11.exception.MemberNotFoundException;
import com.example.sp5ch11.exception.WrongIdPasswordException;
import com.example.sp5ch11.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public AuthInfo authenticate(String email, String password) {
        Member member = memberRepository.findByEmail(email).orElseThrow(WrongIdPasswordException::new);

        if (!member.matchPassword(password)) {
            throw new WrongIdPasswordException();
        }

        return new AuthInfo(member.getSeq(),
                member.getEmail(),
                member.getMb_id());
    }
}
