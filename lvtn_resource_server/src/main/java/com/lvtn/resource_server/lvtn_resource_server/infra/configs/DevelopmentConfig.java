package com.lvtn.resource_server.lvtn_resource_server.infra.configs;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevelopmentConfig {
	@Bean
	public ApplicationRunner dataLoader() {
		return args -> {
		};
	}
}
