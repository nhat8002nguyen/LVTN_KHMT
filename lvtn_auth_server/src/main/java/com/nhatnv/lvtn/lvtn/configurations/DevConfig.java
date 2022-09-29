package com.nhatnv.lvtn.lvtn.configurations;

import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nhatnv.lvtn.lvtn.Entities.User;
import com.nhatnv.lvtn.lvtn.repositories.UserRepository;

@Configuration
public class DevConfig {
	@Bean
	public ApplicationRunner dataLoader(UserRepository userRepo, PasswordEncoder encoder) {
		return args -> {
			User user1 = User.builder()
					.username("customer1")
					.password(encoder.encode("customer1"))
					.role("ROLE_USER")
					.build();

			User user2 = User.builder()
					.username("admin")
					.password(encoder.encode("admin"))
					.role("ROLE_ADMIN")
					.build();

			userRepo.saveAll(Arrays.asList(user1, user2));
		};
	}

}
