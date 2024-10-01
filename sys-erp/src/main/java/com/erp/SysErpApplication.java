package com.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



//모든 시스템에 Spring Security가 자동 적용되는 것을 방지하도록 설정
@SpringBootApplication
public class SysErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysErpApplication.class, args);
	}
	
}
