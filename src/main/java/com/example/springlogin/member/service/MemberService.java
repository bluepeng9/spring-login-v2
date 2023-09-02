package com.example.springlogin.member.service;

import com.example.springlogin.member.domain.Member;
import com.example.springlogin.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    final MemberRepository memberRepository;

    public void join(MemberJoinParam param) {
        log.info("param = {}", param);
        Member member = Member.builder()
                .email(param.email)
                .password(param.password)
                .build();

        memberRepository.save(member);
    }

    public Member login(MemberLoginParam param) {
        log.info("param = {}", param);

        Optional<Member> found = memberRepository.findByEmail(param.email);
        if (found.isEmpty()) {
            throw new RuntimeException();
        }
        Member member = found.get();
        if (!member.getPassword().equals(param.password)) {
            throw new RuntimeException();
        }
        return member;
    }
}
