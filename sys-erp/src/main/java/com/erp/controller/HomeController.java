package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	//메인화면
	@RequestMapping("/")
	public String home() {
		return "/WEB-INF/views/Home.jsp";
	}
	
}
