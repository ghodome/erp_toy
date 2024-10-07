package com.erp.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erp.dao.EmpDao;
import com.erp.dto.EmpDto;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private EmpDao empDao;

	@Override
	public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {
		EmpDto empDto = empDao.selectOneById(empId); // 데이터베이스에서 사용자 정보 검색
		if (empDto == null) {
			throw new UsernameNotFoundException("User not found with empId: " + empId);
		}
		// User 객체를 생성
		return new User(empDto.getEmpId(), empDto.getEmpPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(empDto.getEmpRole())));
	}
}
