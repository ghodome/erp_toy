package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.ApprovalDto;

@Service
public class ApprovalMapper implements RowMapper<ApprovalDto> {

	@Override
	public ApprovalDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ApprovalDto approvalLineDto = new ApprovalDto();
		
		approvalLineDto.setApprovalNo(rs.getInt("approval_no"));
		approvalLineDto.setApprovalDocument(rs.getInt("approval_document"));
		approvalLineDto.setApprovalEmp(rs.getString("approval_emp"));
		approvalLineDto.setApprovalOrder(rs.getInt("approval_order"));
		approvalLineDto.setApprovalStatus(rs.getString("approval_status"));
		
		
		return approvalLineDto;
	}

}
