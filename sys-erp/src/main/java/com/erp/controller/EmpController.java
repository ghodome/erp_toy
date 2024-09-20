package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erp.dao.EmpDao;


@Controller
@RequestMapping("/emp")
public class EmpController {

	
	@Autowired
	private EmpDao empDao;
	
	
}
