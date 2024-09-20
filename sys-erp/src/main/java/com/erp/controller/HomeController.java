package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dao.EmpDao;
import com.erp.dto.EmpDto;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:3000") // React의 포트를 명시
@RestController("/rest")
public class HomeController {

	@Autowired
	private EmpDao empDao;

	@PostMapping("/join")
	public ResponseEntity<String> requestMethodName(@RequestBody EmpDto empDto) {

		System.out.println("result = " + empDto);

		// DB저장
//		empDao.insert(empDto);

		// 성공적으로 처리된 경우
		return ResponseEntity.ok("회원가입이 완료되었습니다.");
	}

}
