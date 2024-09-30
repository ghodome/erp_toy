package com.erp.restcontroller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dao.EmpDao;
import com.erp.dto.EmpDto;
import com.erp.vo.LoginVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.erp.service.EmpService;
import com.erp.util.JwtProvider;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/emp")
public class EmpRestController {

	@Autowired
	private EmpService empService; // EmpService 주입

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private JwtProvider jwtProvider;

	// Create(등록)
	@PostMapping("/")
	public void insert(@RequestBody EmpDto empDto) {

		// 비밀번호 Bcrypt 처리
		String encPw = encoder.encode(empDto.getEmpPassword()); // 비밀번호 해싱
		empDto.setEmpPassword(encPw); // 해싱된 비밀번호로 설정

		// empDao.insert(empDto);
		sqlSession.insert("emp.insert", empDto);

	}

	// 로그인
	// Swagger 처리시 힘들면 추후 분리 예정 -> AuthController로
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginVO loginVO, HttpServletResponse response) {
		EmpDto empDto = sqlSession.selectOne("emp.selectEmpById", loginVO.getEmpId());
		if (empDto != null && encoder.matches(loginVO.getEmpPassword(), empDto.getEmpPassword())) {
			// 토큰(Access / Refresh) 생성
			String accessToken = jwtProvider.generateToken(empDto.getEmpId(), empDto.getEmpEmail(),
					empDto.getEmpRole());
			String refreshToken = jwtProvider.generateRefreshToken(empDto.getEmpId(), empDto.getEmpEmail());
			
			// 기존 쿠키가 있으면 삭제
			Cookie existingAccessToken = new Cookie("accessToken", null);
			existingAccessToken.setPath("/");
			existingAccessToken.setMaxAge(0); // 쿠키 삭제
			response.addCookie(existingAccessToken);

			// Access Token 쿠키 설정
			Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
			accessTokenCookie.setHttpOnly(true); // HttpOnly 설정
			accessTokenCookie.setSecure(false); // HTTPS 환경에서는 true로 설정
			accessTokenCookie.setPath("/");
			accessTokenCookie.setMaxAge(60 * 5); // 5분 만료

			// Refresh Token 쿠키 설정
			Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
			refreshTokenCookie.setHttpOnly(true);
			refreshTokenCookie.setSecure(false); // HTTPS 환경에서는 true로 설정
			refreshTokenCookie.setPath("/");
			refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // 7일 만료

			// 쿠키 응답에 추가
			response.addCookie(accessTokenCookie);
			response.addCookie(refreshTokenCookie);

			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.status(401).body("로그인 실패: 사용자 정보가 올바르지 않습니다.");
		}
	}

	// 로그아웃
	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		// 쿠키 두개를 싹다 지우면 된다
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("accessToken")) {
					Cookie accessToken = new Cookie("accessToken", null);
					accessToken.setPath("/");
					accessToken.setMaxAge(0); // 토큰 삭제
					response.addCookie(accessToken);
				} else if (cookie.getName().equals("refreshToken")) {
					Cookie refreshToken = new Cookie("refreshToken", null);
					refreshToken.setPath("/");
					refreshToken.setMaxAge(0);
					response.addCookie(refreshToken);
				}
			}
		}
	}
	
	
	// 서명 저장
	@PostMapping("/saveSignature")
	public ResponseEntity<String> saveSignature(@RequestBody EmpDto empDto) {
		empService.saveSignature(empDto); // empService 인스턴스 메서드 호출
		return ResponseEntity.ok("Signature saved successfully");
	}
}
