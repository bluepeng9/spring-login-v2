package com.example.springlogin.member.service;

import lombok.Builder;
import lombok.ToString;

@ToString
public class MemberLoginParam {
    String email;
    String password;

    @Builder
    public MemberLoginParam(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
