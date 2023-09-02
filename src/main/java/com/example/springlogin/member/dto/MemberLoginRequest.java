package com.example.springlogin.member.dto;

import lombok.Data;

@Data
public class MemberLoginRequest {
    String email;
    String password;
}
