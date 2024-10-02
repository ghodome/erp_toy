package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.DocumentDto;

@Service
public class DocumentMapper implements RowMapper<DocumentDto>{

	@Override
	public DocumentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentDto documentDto = new DocumentDto();
		
		documentDto.setDocumentNo(rs.getInt("document_no"));
		documentDto.setDocumentTitle(rs.getString("document_title"));
		documentDto.setDocumentContent(rs.getString("document_content"));
		documentDto.setDocumentStatus(rs.getString("document_status"));
		documentDto.setDocumentCreateBy(rs.getString("document_createBy"));
		documentDto.setDocumentCreateAt(rs.getDate("document_createAt"));
		documentDto.setDocumentUpdateAt(rs.getDate("document_updateAt"));
		documentDto.setCategoryCode(rs.getInt("category_code"));
		
		return documentDto;
	}

}
