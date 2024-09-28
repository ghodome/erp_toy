package com.erp.restcontroller;

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
import jakarta.servlet.http.HttpServletResponse;

import com.erp.dto.LoginResponseDto;
import com.erp.util.JwtProvider;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/emp")
public class EmpRestController {

	@Autowired
	private EmpDao empDao;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtProvider jwtProvider;

	// Create(등록)
	@PostMapping("/")
	public void insert(@RequestBody EmpDto empDto) {
		empDao.insert(empDto);
	}

	// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginVO loginVO, HttpServletResponse response) {
		EmpDto empDto = empDao.selectOneById(loginVO.getEmpId());
		if (empDto != null && encoder.matches(loginVO.getEmpPassword(), empDto.getEmpPassword())) {
			// 토큰(Access / Refresh) 생성
			String accessToken = jwtProvider.generateToken(empDto.getEmpId(), empDto.getEmpEmail(), empDto.getEmpRole());
			String refreshToken = jwtProvider.generateRefreshToken(empDto.getEmpId(), empDto.getEmpEmail());

			// LoginResponseDto 객체 생성
			LoginResponseDto responseDto = new LoginResponseDto();
			responseDto.setAccessToken(accessToken);
			responseDto.setRefreshToken(refreshToken);
			
			// Access Token 쿠키 설정
	        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
	        accessTokenCookie.setHttpOnly(true); // HttpOnly 설정
//	        accessTokenCookie.setSecure(true); // Secure 설정 (HTTPS 환경에서)
	        accessTokenCookie.setSecure(false); // Secure 설정 (HTTPS 환경에서)
	        accessTokenCookie.setPath("/"); // Path 설정
	        accessTokenCookie.setMaxAge(60 * 5); // 5분 만료

	        // Refresh Token 쿠키 설정
	        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
	        refreshTokenCookie.setHttpOnly(true);
	        refreshTokenCookie.setSecure(false);
	        refreshTokenCookie.setPath("/");
	        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // 7일 만료

	        // 쿠키 응답에 추가
	        response.addCookie(accessTokenCookie);
	        response.addCookie(refreshTokenCookie);
	        
	        //+추가할거면 리멤버 미 정도?

			return ResponseEntity.ok(responseDto);
		} else {
			return ResponseEntity.status(401).body("로그인 실패: 사용자 정보가 올바르지 않습니다."); // 더 유용한 오류 메시지
		}
	}
}
