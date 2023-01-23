package com.mercadolindo.configs;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

	// http://localhost:8080/swagger-ui/index.html
	@Bean
	public GroupedOpenApi swagger() {
		return GroupedOpenApi.builder().group("com.mercadolindo")
				.packagesToScan("com.mercadolindo.controller").build();
	}

}
	