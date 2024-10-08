package com.erp.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dao.CategoryDao;
import com.erp.dto.CategoryDto;

@RestController
@RequestMapping("/rest/category")
public class CategoryRestController {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@GetMapping("/")
	public List<CategoryDto> list() {
		return categoryDao.selectList(); 
	}
	@GetMapping("/categoryOrigin/{categoryNo}")
	public List<CategoryDto> subList(@PathVariable int categoryNo) {
		return categoryDao.selectListByCategoryOrigin(categoryNo);
	}
	@GetMapping("/categoryDepth/{categoryDepth}")
	public List<CategoryDto> mainList(@PathVariable int categoryDepth) {
		return categoryDao.selectListByCategoryDepth(categoryDepth);
	}
	@PutMapping("/")
	public void edit(@RequestBody CategoryDto categoryDto) {
		categoryDao.update(categoryDto);
	}
	@PostMapping("/")
	public CategoryDto insert(@RequestBody CategoryDto categoryDto) { 
		int sequence = categoryDao.sequence();
		categoryDto.setCategoryCode(sequence);
		categoryDao.insert(categoryDto);
		return categoryDto;
	}
	@GetMapping("/all")
	public List<CategoryDto> allList() {
		return categoryDao.selectListAll();
	}
}
