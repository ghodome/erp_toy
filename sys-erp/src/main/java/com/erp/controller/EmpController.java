package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Autowired
	private PasswordEncoder encoder;

	// 회원가입
	// home.jsp에서 내용 받아오기
	@PostMapping("/join")
	public String join(@ModelAttribute EmpDto empDto) {
		// DB저장
		empDao.insert(empDto);
		return "redirect:/";
	}

	// 로그인
	// home.jsp에서 내용 받아오기
	@PostMapping("/login")
	public String login(@RequestParam String empId // 로그인 아이디
			, @RequestParam String empPassword // 로그인 비밀번호
			, HttpSession session // 세션 -> 로그인 성공시 세션에 필요 내용 첨가
	) {
		EmpDto empDto = empDao.selectOneById(empId);
		if (empDto != null && encoder.matches(empPassword, empDto.getEmpPassword())) {
			session.setAttribute("loginId", empDto.getEmpId());
			return "redirect:/main";
		} else {
			System.out.println("empDto가 null 이여서 메인으로 전환");
			return "redirect:/";
		}
	}

}
