package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.erp.dao.EmpDao;
import com.erp.dto.EmpDto;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	private EmpDao empDao;

	// 회원가입
	// Home.jsp에서 내용 받아오기
	@PostMapping("/join")
	public String join(@ModelAttribute EmpDto empDto) {

		System.out.println("result = " + empDto);
		
		// DB저장
//		empDao.insert(empDto);

		return "redirect:/";
	}

	// 로그인
	// Home.jsp에서 내용 받아오기
	@PostMapping("/login")
	public String login(@RequestParam String empId // 로그인 아이디
			, @RequestParam String empPassword // 로그인 비밀번호
			, HttpSession session // 세션 -> 로그인 성공시 세션에 필요 내용 첨가
			) {

		System.out.println("Content = " + empId + ", " + empPassword);

//		empDao.selectOneById(empId);

		return "redirect:/";
	}

}
