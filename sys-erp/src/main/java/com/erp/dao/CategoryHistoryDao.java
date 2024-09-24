package com.erp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.erp.dto.CategoryHistoryDto;
import com.erp.mapper.CategoryHistoryMapper;
import com.erp.mapper.CategoryMapper;

@Repository
public class CategoryHistoryDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CategoryHistoryMapper categoryHistoryMapper;
	
	//Create
	public int sequence() {
		String sql = "select category_history_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	public void insert(CategoryHistoryDto categoryHistoryDto) {
		String sql = "insert into category_history("
							+ "category_history_no,"
							+ "category_code, "
							+ "category_history_type"
						+ ") values(?, ?, '등록')";
		Object[] data = {
			categoryHistoryDto.getCategoryHistoryNo(),
			categoryHistoryDto.getCategoryCode()
		};
		jdbcTemplate.update(sql, data);
	}
	
}
