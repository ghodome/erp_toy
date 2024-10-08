package com.erp.sys_erp;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.erp.dao.CategoryDao;
import com.erp.dto.CategoryDto;

@SpringBootTest
public class CategoryListTest {

	@Autowired
	private CategoryDao categoryDao;
	
	@Test
	public void test() {
//		List<CategoryDto> list = categoryDao.selectList();
		List<CategoryDto> list = categoryDao.selectListByCategoryDepth(0);
		System.out.println("크기 : " + list.size());
		
		for(CategoryDto categoryDto : list) {
			System.out.println(categoryDto);
		}
	}
	
}
