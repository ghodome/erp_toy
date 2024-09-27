package com.erp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret.key}")
    private String secretKey; // 비밀 키를 주입받음

    private SecretKey getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(); // 키를 바이트 배열로 변환
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String loginId, String email) {
        return Jwts.builder()
                .claim("loginId", loginId) // 커스텀 필드 추가
                .claim("email", email) // 커스텀 필드 추가
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // 1시간
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(String loginId, String email) {
        return Jwts.builder()
        		.claim("loginId", loginId) // 커스텀 필드 추가
                .claim("email", email) // 커스텀 필드 추가
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 604800000)) // 7일
                .signWith(getSigningKey())
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
