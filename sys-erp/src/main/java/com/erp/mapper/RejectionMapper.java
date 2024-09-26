package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.RejectionDto;

@Service
public class RejectionMapper implements RowMapper<RejectionDto> {

	@Override
	public RejectionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RejectionDto rejectionDto = new RejectionDto();
		
		rejectionDto.setRejectionNo(rs.getInt("rejection_no"));
		rejectionDto.setRejectionDocument(rs.getInt("rejection_document"));
		rejectionDto.setRejectionEmp(rs.getString("rejection_emp"));
		rejectionDto.setRejectionReason(rs.getString("rejection_reason"));
		rejectionDto.setRejectionCreateAt(rs.getDate("rejection_createAt"));
		
		return rejectionDto;
	}

}
