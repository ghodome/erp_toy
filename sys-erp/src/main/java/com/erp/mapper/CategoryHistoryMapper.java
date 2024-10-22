package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.CategoryDto;
import com.erp.dto.CategoryHistoryDto;

@Service
public class CategoryHistoryMapper implements RowMapper<CategoryHistoryDto>{
	@Override
	public CategoryHistoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return CategoryHistoryDto.builder()
					.categoryHistoryNo(rs.getInt("category_history_no"))
					.categoryCode(rs.getInt("category_code"))
					.categoryType(rs.getString("category_type"))
					.categoryHistoryTime(rs.getDate("category_history_time"))
				.build();
	}
}
