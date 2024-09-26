package com.erp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dao.EmpDao;
import com.erp.dto.EmpDto;
import com.erp.vo.LoginVO;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/emp")
public class EmpRestController {

	@Autowired
	private EmpDao empDao;

	@Autowired
	private PasswordEncoder encoder;

	// Create(등록)
	@PostMapping("/")
	public void insert(@RequestBody EmpDto empDto) {

		empDao.insert(empDto);

	}

	// 로그인
	// home.jsp에서 내용 받아오기
	@PostMapping("/login")
	public boolean login(@RequestBody LoginVO loginVO) {
		EmpDto empDto = empDao.selectOneById(loginVO.getEmpId());
		if (empDto != null && encoder.matches(loginVO.getEmpPassword(), empDto.getEmpPassword())) {
			return true;
		} else {
			return false;
		}
	}

}
