package com.erp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringdocConfiguration {

	@Bean
	public OpenAPI apiInfo() {
		Info info = new Info().version("v0.0.1").title("erp-mini");
		
		return new OpenAPI().info(info);
	}
}
