package com.erp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

	@Value("${jwt.secret.key}")
	private String secretKey; // 비밀 키를 주입받음

	private SecretKey getSigningKey() {
		// 키를 바이트 배열로 변환(하되 UTF-8 인코딩 방식)
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// 접속 토큰 생성(AccessToken)
	public String generateToken(String loginId, String email, String role) {
		return Jwts.builder().claim("loginId", loginId) // 커스텀 필드 추가
				.claim("email", email) // 커스텀 필드 추가
				.claim("roles", role) // 커스텀 필드 추가
				.issuedAt(new Date()) // 발생일 -> 현재
				.expiration(new Date(System.currentTimeMillis() + 300000))  // 만료일은 5분
				.issuer("KH14MINI")
				.signWith(getSigningKey()) // 서명에 사용할 키 정보
				.compact();
	}

	// 리프레시 토큰 생성(RefreshToken)
	public String generateRefreshToken(String loginId, String email) {
		return Jwts.builder().claim("loginId", loginId) // 커스텀 필드 추가
				.claim("email", email) // 커스텀 필드 추가
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 604800000)) // 7일
				.issuer("KH14MINI")
				.signWith(getSigningKey())
				.compact();
	}

	// 모든 claim 추출
	public Claims extractAllClaims(String token) {
		try {
			// 서명 검증과 함께 Claims 추출
			return Jwts.parser().verifyWith(getSigningKey())
					.requireIssuer("KH14MINI")
					.build().parseSignedClaims(token).getPayload();
		} catch (Exception e) {
			return null; // 예외 발생 시 null 반환
		}
	}

//     Http-Only 쿠키면 애초에 프론트(JS)에서 요청헤더에 못 붙임) --> 미사용
//     요청 헤더에서 JWT 토큰 추출
//	public String resolveToken(HttpServletRequest request) {
//		String bearerToken = request.getHeader("Authorization");
//		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//			return bearerToken.substring(7);
//		}
//		return null;
//	}

	// JWT 유효성 검증 -> 수정안1
	// 모든 클레임을 추출(서명과정+발행자 선 검증)해서 그중 만료일을 찾아낸다
	// 만료일 비교값을 return
	public boolean validateToken(String token) {
		try {
			Claims claims = extractAllClaims(token);
			// 만료일 추출
			Date expiration = claims.getExpiration();
			System.out.println("만료일 = " + expiration);
			// 만료일을 오늘 날짜와 비교하여
			return expiration.after(new Date());
		} catch (Exception e) {
			return false;
		}
	}
}
