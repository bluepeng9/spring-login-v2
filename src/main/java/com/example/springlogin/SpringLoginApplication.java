package com.example.springlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        basePackages = {"com.example"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.example.springlogin.member.controller.**Controller" +
                        "&& !(com.example.springlogin.member.controller.MemberSecurityController)"
                )
        }
)
public class SpringLoginApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringLoginApplication.class, args);
    }

}
