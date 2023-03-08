package com.example.sp5ch11.service;

import com.example.sp5ch11.dto.Member;
import com.example.sp5ch11.exception.DuplicateMemberException;
import com.example.sp5ch11.repository.MemberRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberRegistrerService {

    private final MemberRepository memberRepository;

    public Long regist(String email, String id, String password) {
        Optional<Member> member = memberRepository.findByEmail(email);

        if(!member.isEmpty()){
            throw new DuplicateMemberException("dup email " + email);
        }

        Member newMember = Member.CreateMember(email, id, password);
        memberRepository.save(newMember);

        return newMember.getSeq();
    }
}
