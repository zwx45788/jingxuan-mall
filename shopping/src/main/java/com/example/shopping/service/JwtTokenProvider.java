package com.example.shopping.service;

import com.example.shopping.config.JwtConfig;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import io.jsonwebtoken.Jwts;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;

@Slf4j
public class JwtTokenProvider {
    @Autowired
    private JwtConfig jwtConfig;

    public String generateToken(Long userId) {
        try{
            Instant now = Instant.now();
            Instant expiryDate = now.plusMillis(jwtConfig.getExpiration());

            return Jwts.builder()
                    .subject(String.valueOf(userId))
                    .claim("role","user")
                    .issuedAt(Date.from(now))
                    .expiration(Date.from(expiryDate))
                    .signWith(Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8)))
                    .compact();

        }catch (Exception e){
            log.error("generateToken error",e);
            throw new RuntimeException("generateToken error");
        }
    }


}
