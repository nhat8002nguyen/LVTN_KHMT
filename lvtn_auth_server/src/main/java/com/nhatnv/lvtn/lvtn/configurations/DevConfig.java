package com.nhatnv.lvtn.lvtn.configurations;

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
			userRepo.save(
					new User(
							"user@gmail.com",
							"Nhat Nguyen",
							encoder.encode("password")));

			userRepo.save(
					new User(
							"user1@gmail.com",
							"Anh Nguyen",
							encoder.encode("password1")));
		};
	}

}
