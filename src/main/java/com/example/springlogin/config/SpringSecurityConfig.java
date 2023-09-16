package com.example.springlogin.config;

import com.example.springlogin.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        return http.authorizeHttpRequests(
                        customizer -> customizer
                                .anyRequest().permitAll()
                )
                .formLogin(config -> config
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                )
                .logout(config -> config.logoutSuccessUrl("/"))
                .userDetailsService(userDetailsService)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(MemberService service) {
        return new UserDetailsServiceImpl(service);
    }
}