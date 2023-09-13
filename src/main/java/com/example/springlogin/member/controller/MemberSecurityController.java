package com.example.springlogin.member.controller;

import com.example.springlogin.member.dto.MemberJoinRequest;
import com.example.springlogin.member.dto.MemberLoginRequest;
import com.example.springlogin.member.service.MemberJoinParam;
import com.example.springlogin.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberSecurityController {

    final MemberService memberService;

    @GetMapping("/")
    public String getHomePage(Authentication authentication, Model model) {

        if (authentication != null) {
            String name = authentication.getName();
            model.addAttribute("email", name);
        }

        return "home";
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

    @GetMapping("/join")
    public String getJoinPage(Long memberId, Model model) {
        model.addAttribute("memberJoinRequest", new MemberJoinRequest());
        return "join";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("memberLoginRequest", new MemberLoginRequest());
        return "login";
    }
}
