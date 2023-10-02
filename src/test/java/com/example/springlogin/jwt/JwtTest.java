package com.example.springlogin.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
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
                .setSubject(String.valueOf(user.id))
                .setExpiration(createExpiration())
                .signWith(SignatureAlgorithm.HS256, createSign());

        String compact = builder.compact();
        log.info("compact = {}", compact);
    }

    @Test
    public void validCheck() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJleHAiOjE2OTY0NDE2NzF9.nvkZCF2zCk8c-d9gixa7FGL8GLLYufcoFffzKBB8nK8";
        String secret = "12341234123412341234123412341234";
//        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(SignatureAlgorithm.HS256, new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName()));
//        String[] split = token.split("\\.");
//        boolean valid = validator.isValid(split[0] + "." + split[1], split[2]);
//        log.info("valid = {}", valid);

        Jwts.parser().setSigningKey(secret.getBytes())
                .parseClaimsJws(token).getBody();
    }


    private Date createExpiration() {
        return new Date(System.currentTimeMillis() + 1000 * 60);
    }

    private Key createSign() {
        String secret = "12341234123412341234123412341234";
        return new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
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
