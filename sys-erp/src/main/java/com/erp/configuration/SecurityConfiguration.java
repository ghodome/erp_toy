package com.erp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.erp.util.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	// 스프링 부트 시큐리티는 첫번째로 CSRF 토큰이 없으면 403 에러를 내보낸다
	// 근데 이부분은 초반에 CSRF 설정을 해제했기때문에 적용사항이 아니었음
	// -> 다음 문제 발생 ( 스프링 부트 시큐리티가 원초적으로 403 에러를 내보낸다)
	// 해결방법 핸들러를 만든다..

	// 미 인증 사용자가 접근할 때 401 Unauthorized 반환
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	// 권한이 없는 사용자가 접근할 때 403 Forbidden 반환
	@Autowired
	private JwtAccessDeniedHandler jwtAccessDeniedHandler;

	// 오늘만 사는 코딩
	// 정적으로 선언하지 않으면 의존성 순환 발생...
	// 일단 나중에 해결
	@Bean
	public static PasswordEncoder encoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// 원하는 설정을 추가
		return encoder;
	}

	// Spring Security의 보안 설정
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				// csrf 인증 해제
				.csrf(csrf -> csrf.disable()).exceptionHandling(exception -> exception
						// 인증 실패 시 401 반환
						.authenticationEntryPoint(jwtAuthenticationEntryPoint)
						// 권한 부족 시 403 반환
						.accessDeniedHandler(jwtAccessDeniedHandler))

				.authorizeHttpRequests(auth -> auth
						// /emp/me 엔드포인트는 인증 필요
						.requestMatchers("/emp/me").authenticated().anyRequest().permitAll() // 그 외의 모든 요청은 인증 없이 접근 허용
				)

				// 세션 정책 설정 (상태 비저장)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// JWT 필터 추가
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public UrlBasedCorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:3000"); // 허용할 출처
		// 모든 HTTP 메소드(GET, POST, PUT, DELETE 등)를 허용
		configuration.addAllowedMethod("*"); // 모든 HTTP 메소드 허용
		configuration.addAllowedHeader("*"); // 모든 헤더 허용
		configuration.setAllowCredentials(true); // 자격 증명 허용

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 적용
		return source;
	}

}
