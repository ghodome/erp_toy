package com.erp.util;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.erp.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String[] excludePath = { "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**", "/api-docs/json/swagger-config",
				"/api-docs/json", };
		String path = request.getRequestURI();
		return Arrays.stream(excludePath).anyMatch(path::startsWith);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {

	    // 쿠키에서 토큰 추출
	    Cookie[] cookies = request.getCookies();
	    String accessToken = null;

	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("accessToken")) {
	                accessToken = cookie.getValue();
	            }
	        }
	    }
	    System.out.println("Cookies = " + accessToken);
	    // 토큰 검증 및 인증 설정
	    if (accessToken != null) {
	        if (jwtProvider.validateToken(accessToken)) {
	            String username = jwtProvider.extractAllClaims(accessToken).get("loginId", String.class);
	            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
	            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
	                    userDetails.getAuthorities());
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            System.out.println("Now you have token!");
	        } else {
	            System.out.println("Invalid token: " + accessToken);
	        }
	    } else {
	        System.out.println("Access token is missing.");
	    }

	    filterChain.doFilter(request, response);
	}

}
