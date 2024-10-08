package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erp.dao.CategoryDao;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@RequestMapping("/")
	public String home() {
		return "/WEB-INF/views/category/home.jsp";
	}
	
}
