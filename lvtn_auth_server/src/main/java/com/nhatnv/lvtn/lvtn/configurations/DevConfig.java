package com.nhatnv.lvtn.lvtn.configurations;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nhatnv.lvtn.lvtn.repositories.UserRepository;

@Configuration
public class DevConfig {
	@Bean
	public ApplicationRunner dataLoader(UserRepository userRepo, PasswordEncoder encoder) {
		return args -> {
			// User user1 = User.builder()
			// .username("customer1")
			// .firstName("Nhat")
			// .lastName("Nguyen")
			// .email("aominenguyendu@gmail.com")
			// .password(encoder.encode("customer1"))
			// .about("I am a software engineer")
			// .phone("0946081581")
			// .role("ROLE_USER")
			// .build();

			// User user2 = User.builder()
			// .username("admin")
			// .firstName("Anh")
			// .lastName("Nguyen")
			// .email("anhNguyen@gmail.com")
			// .password(encoder.encode("admin1"))
			// .about("I am a Traveling guider")
			// .phone("0946081581")
			// .role("ROLE_ADMIN")
			// .build();

			// userRepo.saveAll(Arrays.asList(user1, user2));
		};
	}

}
