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
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginVO loginVO) {
		EmpDto empDto = empDao.selectOneById(loginVO.getEmpId());
		if (empDto != null && encoder.matches(loginVO.getEmpPassword(), empDto.getEmpPassword())) {
			String accessToken = jwtProvider.generateToken(empDto.getEmpId(), empDto.getEmpEmail());
			String refreshToken = jwtProvider.generateRefreshToken(empDto.getEmpId(), empDto.getEmpEmail());

			// 토큰 내용을 콘솔에 출력
			System.out.println("Access Token: " + accessToken);
			System.out.println("Refresh Token: " + refreshToken);

			// LoginResponseDto 객체 생성
			LoginResponseDto responseDto = new LoginResponseDto();
			responseDto.setAccessToken(accessToken);
			responseDto.setRefreshToken(refreshToken);

			return ResponseEntity.ok(responseDto);
		} else {
			return ResponseEntity.status(401).body(null); // Unauthorized
		}
	}
}
