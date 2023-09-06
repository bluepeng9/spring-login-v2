package com.example.springlogin.member.service;

import com.example.springlogin.member.domain.Member;
import com.example.springlogin.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    final MemberRepository memberRepository;
    final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(MemberJoinParam param) {
        log.info("param = {}", param);

        Member member = Member.builder()
                .email(param.email)
                .password(passwordEncoder.encode(param.password))
                .build();

        memberRepository.save(member);
    }

    public Member getUser(Long id) {
        Optional<Member> found = memberRepository.findById(id);
        return found.orElse(null);
    }

    public Member login(MemberLoginParam param) {
        log.info("param = {}", param);

        Optional<Member> found = memberRepository.findByEmail(param.email);
        if (found.isEmpty()) {
            throw new RuntimeException();
        }
        Member member = found.get();
        if (!passwordEncoder.matches(param.password, member.getPassword())) {
            throw new RuntimeException();
        }
        return member;
    }
}
