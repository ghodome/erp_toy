package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.CategoryDto;

@Service
public class CategoryMapper implements RowMapper<CategoryDto>{
	@Override
	public CategoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return CategoryDto.builder()
					.categoryCode(rs.getInt("category_code"))
					.categoryName(rs.getString("category_name"))
					.categoryNote(rs.getString("category_note"))
					.categoryEnable(rs.getString("category_enable"))
					.categoryGroup(rs.getInt("category_group"))
					.categoryOrigin(rs.getInt("category_origin"))
					.categoryDepth(rs.getInt("category_depth"))
				.build();
	}
}
