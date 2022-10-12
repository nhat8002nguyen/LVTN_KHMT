package com.lvtn.resource_server.lvtn_resource_server.infra.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.lvtn.resource_server.lvtn_resource_server.infra.common.props.JwtIssuerProps;

@EnableWebSecurity
public class SecurityConfig {
	private JwtIssuerProps jwtIssuerProps;

	public SecurityConfig(JwtIssuerProps jwtIssuerProps) {
		this.jwtIssuerProps = jwtIssuerProps;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(
				authorize -> authorize
						.anyRequest().permitAll())
				.csrf().disable()
				// .antMatchers(HttpMethod.GET, "/data-api/posts").permitAll()
				// .antMatchers(HttpMethod.POST,
				// "/data-api/posts").hasAuthority("SCOPE_writePosts")
				// .antMatchers(HttpMethod.DELETE,
				// "/data-api/posts").hasAuthority("SCOPE_deletePosts")
				// .anyRequest()
				// .authenticated())
				// .oauth2ResourceServer(oauth2 -> oauth2.jwt())
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
	// public JwtDecoder jwtDecoder() {
	// return JwtDecoders.fromIssuerLocation(jwtIssuerProps.getIssuerUri());
	// }
}
