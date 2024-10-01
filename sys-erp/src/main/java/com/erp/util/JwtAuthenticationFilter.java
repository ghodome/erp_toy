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
		String[] excludePath = {"/**","/swagger-ui/**", "/v3/**", "/swagger-resources/**", "/webjars/**", "/api-docs/**" };
		String path = request.getRequestURI();

		// 경로가 매칭되는지 확인
		return Arrays.stream(excludePath).anyMatch(pattern -> path.matches(pattern.replace("**", ".*")));
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 요청 URI 로그 추가
		System.out.println("====================In Filter======================");
		System.out.println("Filtering request for URI: " + request.getRequestURI());

		// 쿠키에서 액세스 토큰 및 리프레시 토큰 추출
		Cookie[] cookies = request.getCookies();
		String accessToken = null;
		String refreshToken = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("accessToken")) {
					accessToken = cookie.getValue();
					System.out.println("AccessToken: " + accessToken);
				} else if (cookie.getName().equals("refreshToken")) {
					refreshToken = cookie.getValue();
				}
			}
		}

		
		System.out.println("리프레시 토큰 : " + refreshToken);

		// 액세스 토큰 검증 및 인증 설정
		if (accessToken != null && jwtProvider.validateToken(accessToken)) {
			// 현재 존재하는 accessToken에서 loginId의 클레임 값을 가져옴
			String username = jwtProvider.extractAllClaims(accessToken).get("loginId", String.class);
			
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			System.out.println("상태 : Accesstoken 존재!");
		} else if (accessToken == null && refreshToken != null && jwtProvider.validateToken(refreshToken)) {
			// accessToken이 없으므로 Refresh토큰에서 아이디를 가져옴 + 서명 인증도 함
			String username = jwtProvider.extractAllClaims(refreshToken).get("loginId", String.class);

			EmpDto empDto = sqlSession.selectOne("emp.selectEmpById", username);

			// 리프레시 토큰이 있는지 없는지 확인
			System.out.println("AccessToken이 없을때의 리프레시토큰 : " + refreshToken);
			
			// 리프레시 토큰이 유효한 경우 새로운 액세스 토큰 생성
			String newAccessToken = jwtProvider.generateToken(empDto.getEmpId(), empDto.getEmpEmail(),
					empDto.getEmpRole());
			
			// 새로운 액세스 토큰을 쿠키에 설정
			Cookie newAccessTokencookie = new Cookie("accessToken", newAccessToken);
			newAccessTokencookie.setPath("/"); // 모든 경로에서 유효하도록 설정
			newAccessTokencookie.setHttpOnly(true);
			newAccessTokencookie.setSecure(false); // HTTPS 환경에서는 true로 설정
			newAccessTokencookie.setMaxAge(60 * 5);
			
			response.addCookie(newAccessTokencookie);
			System.out.println("새로운 AccessToken 발급!");
			response.setStatus(HttpServletResponse.SC_OK); // 상태 코드 200
		} else {
			System.out.println("유효하지않은 토큰 또는 토큰이 없습니다.");
		}

		filterChain.doFilter(request, response);
		System.out.println("===================================================");
	}

}
