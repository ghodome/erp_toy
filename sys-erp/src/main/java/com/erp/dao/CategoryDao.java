package com.erp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.erp.dto.CategoryDto;
import com.erp.mapper.CategoryMapper;

@Repository
public class CategoryDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	//Create
	public int sequence() {
		String sql = "select category_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	public void insert(CategoryDto categoryDto) {
		String sql = "insert into category("
							+ "category_code, category_name, category_note, "
							+ "category_enable, category_group, category_origin, "
							+ "category_depth"
						+ ") values(?, ?, ?, 'Y', ?, ?, ?)";
		Object[] data = {
			categoryDto.getCategoryCode(), categoryDto.getCategoryName(),
			categoryDto.getCategoryNote(), categoryDto.getCategoryGroup(), 
			categoryDto.getCategoryOrigin(), categoryDto.getCategoryDepth()
		};
		jdbcTemplate.update(sql, data);
	}
	
}
