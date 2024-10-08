package com.erp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import io.swagger.v3.oas.models.Components;

@Configuration
public class SpringdocConfiguration {

    @Bean
    public OpenAPI apiInfo() {
        Info info = new Info().version("v0.0.1").title("erp-mini");

        return new OpenAPI()
                .info(info)
                .addSecurityItem(new SecurityRequirement()
                    .addList("Bearer Authentication")) // 보안 요구 사항 이름
                .components(new Components()
                    .addSecuritySchemes("Bearer Authentication", // 보안 스키마 이름
                        new SecurityScheme()
                            .name("Authorization")
                            .type(SecurityScheme.Type.HTTP) // Type.APIKEY에서 HTTP로 변경
                            .scheme("bearer")
                            .bearerFormat("JWT")));
    }
}

