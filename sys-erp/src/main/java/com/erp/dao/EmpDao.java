package com.erp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.erp.dto.EmpDto;
import com.erp.mapper.EmpMapper;

@Repository
public class EmpDao {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private EmpMapper empMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// C
	// 사원 정보 입력 -> join.jsp에서 받아오는 값 + 비밀번호 암호화 저장 
	public void insert(EmpDto empDto) {
		// sql문 작성
		String sql = "INSERT INTO emp (" // 자동 정렬시 줄바뀜 방지 주석
				+ "emp_id, emp_password, emp_user_type, emp_name, " // 기본정보 - 1
				+ "emp_level, emp_dept, emp_gender, emp_hp, emp_email, emp_birth, emp_sdate, " // 기본정보 - 2
				+ "emp_edu, emp_memo, " // 옵션 -1
				+ "emp_post, emp_address1, emp_address2," // 이메일
				+ "emp_role, emp_signature)" // 전자 서명
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// 비밀번호 Bcrypt 처리
		String encPw = encoder.encode(empDto.getEmpPassword()); // 비밀번호 해싱
		empDto.setEmpPassword(encPw); // 해싱된 비밀번호로 설정

		Object[] data = { empDto.getEmpId(), empDto.getEmpPassword(), empDto.getEmpUserType(), empDto.getEmpName(), // 기본정보
				empDto.getEmpLevel(), empDto.getEmpDept(), empDto.getEmpGender(), empDto.getEmpHp(),
				empDto.getEmpEmail(), empDto.getEmpBirth(), empDto.getEmpBirth(), empDto.getEmpSdate() };

		jdbcTemplate.update(sql, data);
	}

	// R
	// [1] 사원의 Id로 정보 조회
	public EmpDto selectOneById(String empId) {
		String sql = "select * from emp where empId = ?";
		Object[] data = { empId };
		List<EmpDto> list = jdbcTemplate.query(sql, empMapper, data);
		return list.isEmpty() ? null : list.get(0);
	}

	// U
	// D

}
