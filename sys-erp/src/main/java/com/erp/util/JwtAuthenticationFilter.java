package com.erp.util;

import java.io.IOException;
import java.util.Arrays;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.erp.dto.EmpDto;
import com.erp.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String[] excludePath = { "/swagger-ui/**", "/v3/**", "/swagger-resources/**", "/webjars/**", "/api-docs/**" };
		String path = request.getRequestURI();

		// 경로가 매칭되는지 확인
		return Arrays.stream(excludePath).anyMatch(pattern -> path.matches(pattern.replace("**", ".*")));
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
				if (cookie.getName().equals("accessToken")) {
					accessToken = cookie.getValue();
					System.out.println("AccessToken: " + accessToken);
				} else if (cookie.getName().equals("refreshToken")) {
					refreshToken = cookie.getValue();
					System.out.println("리프레시 토큰 : " + refreshToken);
				}
			}
		}

		if (accessToken != null && jwtProvider.validateToken(accessToken)) {
			// 유효한 액세스 토큰인 경우, 사용자 인증 처리
			username = jwtProvider.extractAllClaims(accessToken).get("loginId", String.class);
			System.out.println("상태 : 유효한 AccessToken 존재!");

		} else if (refreshToken != null && jwtProvider.validateToken(refreshToken)) {
			// 액세스 토큰이 없고 리프레시 토큰이 유효한 경우
			username = jwtProvider.extractAllClaims(refreshToken).get("loginId", String.class);
			EmpDto empDto = sqlSession.selectOne("emp.selectEmpById", username);

			// 새로운 액세스 토큰 생성
			String newAccessToken = jwtProvider.generateToken(empDto.getEmpId(), empDto.getEmpEmail(),
					empDto.getEmpRole());

			// 새로운 액세스 토큰을 쿠키에 설정
			Cookie newAccessTokencookie = new Cookie("accessToken", newAccessToken);
			newAccessTokencookie.setPath("/");
			newAccessTokencookie.setHttpOnly(true);
			newAccessTokencookie.setSecure(false);
			newAccessTokencookie.setMaxAge(60 * 5); // 5분

			response.addCookie(newAccessTokencookie);
			System.out.println("새로운 AccessToken 발급!");

			// 사용자를 인증 처리
			username = jwtProvider.extractAllClaims(newAccessToken).get("loginId", String.class);			
		}
		
		// 사용자 인증 처리
	    if (username != null) {
	        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
	        
	        if (userDetails != null) {
	            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
	                    null, userDetails.getAuthorities());
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        }
	    }
		

		filterChain.doFilter(request, response);
		System.out.println("===================================================");
	}

}
