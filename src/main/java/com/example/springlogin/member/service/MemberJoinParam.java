package com.example.springlogin.member.service;


import lombok.Builder;
import lombok.ToString;

@ToString
public class MemberJoinParam {
    String email;
    String password;

    @Builder
    public MemberJoinParam(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
