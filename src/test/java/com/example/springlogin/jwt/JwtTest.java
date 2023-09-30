package com.example.springlogin.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtTest {

    @Test
    public void generateJwtToken() {
        User user = new User(123L);
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims())
                .setSubject(String.valueOf(user.id));

        String compact = builder.compact();
        log.info("compact = {}", compact);
    }

    private Map<String, Object> createClaims() {
        Map<String, Object> claims = new HashMap<>();
        return claims;
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        return header;
    }

    static class User {
        Long id;

        public User(Long id) {
            this.id = id;
        }

    }
}
