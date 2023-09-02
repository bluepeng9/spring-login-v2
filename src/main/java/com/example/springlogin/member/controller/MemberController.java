package com.example.springlogin.member.controller;

import com.example.springlogin.member.domain.Member;
import com.example.springlogin.member.dto.MemberJoinRequest;
import com.example.springlogin.member.dto.MemberLoginRequest;
import com.example.springlogin.member.service.MemberJoinParam;
import com.example.springlogin.member.service.MemberLoginParam;
import com.example.springlogin.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    final MemberService memberService;

    @GetMapping("/")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/join")
    public String getJoinPage(Long memberId, Model model) {
        model.addAttribute("memberJoinRequest", new MemberJoinRequest());
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("memberJoinRequest") MemberJoinRequest request) {
        log.info("request = {}", request);
        MemberJoinParam param = MemberJoinParam.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        memberService.join(param);
        return "redirect:";
    }


    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("memberLoginRequest", new MemberLoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("memberLoginRequest") MemberLoginRequest request) {
        log.info("request = {}", request);
        MemberLoginParam param = MemberLoginParam.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        Member found = memberService.login(param);
        return "redirect:";
    }
}
