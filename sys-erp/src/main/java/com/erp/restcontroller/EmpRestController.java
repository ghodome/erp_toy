package com.erp.restcontroller;

import java.util.HashMap;
import java.util.Map;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
			String refreshToken = jwtProvider.generateRefreshToken(empDto.getEmpId(), empDto.getEmpEmail(),
					loginVO.isRemeberMe());

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
			// RememberMe 옵션에 따라
			if (loginVO.isRemeberMe()) {
				// 쿠키 만료일 1년으로
				refreshTokenCookie.setMaxAge(365 * 24 * 60 * 60);
			} else {
				// 쿠키 만료일 7일으로
				refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60);
			}

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
	// 서명 저장 (EmpRestController.java 수정)
	@PostMapping("/saveSignature")
	public ResponseEntity<String> saveSignature(@RequestBody Map<String, String> payload) {
	    String empNo = payload.get("empNo");
	    String empSignature = payload.get("empSignature");

	    // 로그 추가
	    System.out.println("Received signature data: " + empSignature.substring(0, Math.min(50, empSignature.length())) + "...");

	    // 서명 데이터 검증
	    if (empSignature == null || !empSignature.startsWith("data:image/")) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("올바른 서명 데이터를 제공해 주세요.");
	    }

	    try {
	        // empNo로 사원 정보 조회
	        EmpDto empDto = empService.findEmpByNo(empNo);
	        if (empDto == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사원을 찾을 수 없습니다.");
	        }

	        // 서명 저장
	        empDto.setEmpSignature(empSignature);
	        empService.updateEmp(empDto);

	        return ResponseEntity.ok("서명이 성공적으로 저장되었습니다.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서명 저장 중 오류가 발생했습니다.");
	    }
	}



	// 인증된 사용자 정보를 반환하는 엔드포인트
//    @GetMapping("/me")
//    public ResponseEntity<?> getCurrentUser() {
//        // 현재 인증된 사용자 정보 가져오기
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = (String) authentication.getName();  // 인증된 사용자의 username
//        
//        // 데이터베이스에서 사용자 정보 조회
//        EmpDto empDto = sqlSession.selectOne("emp.selectEmpById", username);
//
//        if (empDto != null) {
//            // 사용자 정보 반환
//            return ResponseEntity.ok(empDto);
//        } else {
//            return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.");
//        }
//    }
	// 인증된 사용자 정보를 반환하는 엔드포인트
	@PostMapping("/me")
	public Map<String, String> getCurrentUser() {
	    // 현재 인증된 사용자 정보 가져오기
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated()) {
	        Object principal = authentication.getPrincipal();

	        String username = null;
	        String userRole = null;

	        if (principal instanceof UserDetails) {
	            // UserDetails로 캐스팅하여 사용자 정보 가져오기
	            UserDetails userDetails = (UserDetails) principal;
	            username = userDetails.getUsername(); // 사용자 ID (emp_id 또는 emp_no일 수 있음)
	            userRole = userDetails.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority)
	                    .orElse("No authority").trim();
	        } else {
	            // principal이 String 타입인 경우 (예: 사용자 이름)
	            username = principal.toString();
	            userRole = "No authority"; // 기본값 설정
	        }

	        System.out.println("userDetails = " + username + " " + userRole);

	        // Map 객체 생성
	        Map<String, String> userInfo = new HashMap<>(); // HashMap으로 생성

	        // 사용자 이름과 권한 추가
	        userInfo.put("userName", username);
	        userInfo.put("userRole", userRole);

	        // 데이터베이스에서 사용자 정보 조회
	        EmpDto empDto = sqlSession.selectOne("emp.selectEmpById", username); // 사용자 ID로 사원 정보 조회
	        if (empDto != null) {
	            // 사원번호와 휴대전화 추가
	            userInfo.put("empNo", empDto.getEmpNo());
	            userInfo.put("empHp", empDto.getEmpHp());
	            userInfo.put("empSignature", empDto.getEmpSignature()); // 서명 추가
	        }

	        System.out.println(userInfo);
	        return userInfo; // 사용자 정보 반환
	    }
	    return null; // 사용자 정보가 없을 경우 처리
	}
}
