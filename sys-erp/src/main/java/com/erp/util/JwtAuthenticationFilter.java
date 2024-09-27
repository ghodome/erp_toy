package com.erp.util;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtProvider jwtProvider;
	
	// --> Filter에서 걸러짐
	@Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {
            "/swagger-ui/index.html",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-initializer.js",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/index.css",
            "/swagger-ui/favicon-32x32.png",
            "/swagger-ui/favicon-16x16.png",
            "/api-docs/json/swagger-config",
            "/api-docs/json"
        };
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		 // 요청에서 JWT 추출 --> Authorization
		 String token = jwtProvider.resolveToken(request);
		 
		 // 토큰이 유효한 경우
		 if(token != null && jwtProvider.validateToken(token)) {
			 
			 // 토큰 내용 중에서 loginId라고 선언된 내용의 값을 추출
			 String loginId = jwtProvider.extractAllClaims(token).get("loginId", String.class); // 토큰에서 사용자 정보 추출
			 
			 // 아마 여기서 EmpDto의 존재 유무를 판단
			 
			 // EmpDto가 존재하면
			 
		 }
		
	}

}
