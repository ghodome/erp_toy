package com.erp.dao;

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
	
	//C
	public void insert(EmpDto empDto) {
		//sql문 작성
		String sql = "";
		
		// 비밀번호 Bcrypt 처리
        String encPw = encoder.encode(empDto.getEmpPassword()); // 비밀번호 해싱
        empDto.setEmpPassword(encPw); // 해싱된 비밀번호로 설정
        
		Object[] data = {};
		
		jdbcTemplate.update(sql, data);
	}
	//R
	//U
	//D

}
