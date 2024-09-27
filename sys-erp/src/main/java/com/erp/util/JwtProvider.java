package com.erp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

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

    //모든 claim 추출
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // 요청 헤더에서 JWT 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // JWT 유효성 검증 -> 향상 예정, 지금당장은 단순 추출 하여 있을경우 true 반환
	public boolean validateToken(String token) {

		try {
			extractAllClaims(token);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
