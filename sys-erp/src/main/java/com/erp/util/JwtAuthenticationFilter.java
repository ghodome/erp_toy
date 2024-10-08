// src/main/java/com/erp/util/JwtAuthenticationFilter.java
package com.erp.util;

import java.io.IOException;
import java.util.Arrays;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.erp.dto.EmpDto;
import com.erp.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	private static final AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String[] excludePath = { "/emp/login", "/swagger-ui/**", "/v3/**", "/swagger-resources/**", "/webjars/**",
				"/api-docs/**" };
		String path = request.getRequestURI();

		// AntPathMatcher를 사용하여 경로 매칭
		return Arrays.stream(excludePath).anyMatch(pattern -> pathMatcher.match(pattern, path));
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("====================In Filter======================");
		System.out.println("Filtering request for URI: " + request.getRequestURI());

		Cookie[] cookies = request.getCookies();
		String accessToken = null;
		String refreshToken = null;
		String username = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("accessToken".equals(cookie.getName())) {
					accessToken = cookie.getValue();
					System.out.println("AccessToken: " + accessToken);
				} else if ("refreshToken".equals(cookie.getName())) {
					refreshToken = cookie.getValue();
					System.out.println("리프레시 토큰 : " + refreshToken);
				}
			}
		}

		try {
			if (accessToken != null && jwtProvider.validateToken(accessToken)) {
				// 유효한 액세스 토큰인 경우, 사용자 인증 처리
				username = jwtProvider.extractAllClaims(accessToken).get("loginId", String.class);
				System.out.println("상태 : 유효한 AccessToken 존재!");
			} else if (refreshToken != null && jwtProvider.validateToken(refreshToken)) {
				// 액세스 토큰이 없고 리프레시 토큰이 유효한 경우
				username = jwtProvider.extractAllClaims(refreshToken).get("loginId", String.class);
				EmpDto empDto = sqlSession.selectOne("emp.selectEmpById", username);

				if (empDto == null) {
					throw new RuntimeException("사용자를 찾을 수 없습니다."); // 사용자 정보가 없을 때 예외 발생
				}

				// 새로운 액세스 토큰 생성
				String newAccessToken = jwtProvider.generateToken(empDto.getEmpId(), empDto.getEmpEmail(),
						empDto.getEmpRole());

				// 새로운 액세스 토큰을 쿠키에 설정
				Cookie newAccessTokenCookie = new Cookie("accessToken", newAccessToken);
				newAccessTokenCookie.setPath("/");
				newAccessTokenCookie.setHttpOnly(true);
				newAccessTokenCookie.setSecure(false); // HTTPS 환경에서는 true로 설정
				newAccessTokenCookie.setMaxAge(60 * 5); // 5분

				response.addCookie(newAccessTokenCookie);
				System.out.println("새로운 AccessToken 발급!");

				// 사용자 인증 처리
				username = jwtProvider.extractAllClaims(newAccessToken).get("loginId", String.class);
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

				if (userDetails == null) {
					throw new RuntimeException("사용자 인증 실패."); // 인증 실패 시 예외 발생
				}

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
				System.out.println("사용자 인증 완료: " + username);
			} else if (accessToken == null && refreshToken == null) {
				// 토큰이 유효하지 않거나 사용자 정보를 찾을 수 없는 경우 401 반환
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
				return;
			}

		} catch (Exception e) {
			// 401 오류 응답
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing token");
			return;
		}

		filterChain.doFilter(request, response);
		System.out.println("===================================================");
	}
}
