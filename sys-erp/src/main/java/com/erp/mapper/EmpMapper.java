package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.erp.dto.EmpDto;

public class EmpMapper implements RowMapper<EmpDto> {

	@Override
	public EmpDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmpDto empDto = new EmpDto();
		empDto.setEmpId(rs.getString("emp_id"));
		empDto.setEmpPassword(rs.getString("emp_password"));
		empDto.setEmpUserType(rs.getString("emp_user_type"));
		empDto.setEmpNo(rs.getString("emp_no"));
		empDto.setEmpName(rs.getString("emp_name"));
		empDto.setEmpLevel(rs.getString("emp_level"));
		empDto.setEmpDept(rs.getString("emp_dept"));
		return empDto;
	}
	
}
