package com.lvtn.resource_server.lvtn_resource_server.infra.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

import com.lvtn.resource_server.lvtn_resource_server.infra.common.props.JwtIssuerProps;

@EnableWebSecurity
public class SecurityConfig {
	private JwtIssuerProps jwtIssuerProps;

	@Autowired
	public SecurityConfig(JwtIssuerProps jwtIssuerProps) {
		this.jwtIssuerProps = jwtIssuerProps;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(
				authorize -> authorize
						// .anyRequest().permitAll())
						.antMatchers(HttpMethod.GET, "/api/v1/posts/*")
						.hasAuthority("SCOPE_posts.read")
						.antMatchers(HttpMethod.POST,
								"/api/v1/posts")
						.hasAuthority("SCOPE_posts.write")
						.antMatchers(HttpMethod.DELETE,
								"/api/v1/posts")
						.hasAuthority("SCOPE_posts.write")
						.antMatchers("/api/v1/user/*")
						.hasAuthority("SCOPE_posts.read")
						.anyRequest()
						.authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())))
				.csrf().disable()
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		return JwtDecoders.fromIssuerLocation(jwtIssuerProps.getIssuerUri());
	}
}
