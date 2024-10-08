package com.erp.dao;

import java.util.List;

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
	
	//Read
	public List<CategoryDto> selectList() {
		String sql = "select * from category order by category_code asc";
		return jdbcTemplate.query(sql, categoryMapper);
	}
	public List<CategoryDto> selectListByCategoryOrigin(int categoryNo) {
		String sql = "select * from category "
						+ "where category_origin=? "
						+ "order by category_code asc";
		Object[] data = {categoryNo};
		return jdbcTemplate.query(sql, categoryMapper, data);
	}
	public List<CategoryDto> selectListByCategoryDepth(int categoryDepth) {
		String sql = "select * from category "
						+ "where category_depth=? "
						+ "order by category_code asc";
		Object[] data = {categoryDepth};
		return jdbcTemplate.query(sql, categoryMapper, data);
	}
	public void update(CategoryDto categoryDto) {
		
	}
	public List<CategoryDto> selectListAll() {
		String sql = "select * from category "
						+ "connect by prior category_code = category_origin "
						+ "start with category_origin is null "
						+ "order siblings by category_group asc, category_code asc";
		return jdbcTemplate.query(sql, categoryMapper);
	} 
	
	
}
