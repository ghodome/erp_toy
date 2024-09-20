package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.EmpDto;

@Service
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
		empDto.setEmpGender(rs.getString("emp_gender"));
		empDto.setEmpHp(rs.getString("emp_hp"));
		empDto.setEmpEmail(rs.getString("emp_email"));
		empDto.setEmpBirth(rs.getDate("emp_birth"));
		empDto.setEmpEdu(rs.getString("emp_edu"));
		empDto.setEmpSdate(rs.getDate("emp_sdate"));
		empDto.setEmpMemo(rs.getString("emp_memo"));
		empDto.setEmpPost(rs.getString("emp_post"));
		empDto.setEmpAddress1(rs.getString("emp_address1"));
		empDto.setEmpAddress2(rs.getString("emp_address2"));
		empDto.setEmpRole(rs.getString("emp_role"));
		empDto.setEmpSignature(rs.getString("emp_signature"));
		return empDto;
	}
	
}
