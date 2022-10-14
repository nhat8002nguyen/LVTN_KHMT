package com.nhatnv.lvtn.lvtn.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nhatnv.lvtn.lvtn.repositories.UserRepository;

@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeRequests(
				authorizeRequests -> authorizeRequests
						.antMatchers("/api/v1/auth/sign-up").permitAll()
						.anyRequest().authenticated())
				.formLogin()
				.and()
				.csrf().disable()
				.build();
	}

	@Bean
	UserDetailsService detailsService(UserRepository userRepo) {
		return username -> userRepo.findByUsername(username);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
