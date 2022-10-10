package com.lvtn.resource_server.lvtn_resource_server.infra.configs;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.concrete_services.AdminPostServiceImpl;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.concrete_services.UserPostServiceImpl;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.repositories.post_repository.PostRepository;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.AdminPostService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.PostDelectionService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.TrendingPostService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.UserPostService;

@Configuration
public class BeanConfigs {

	public enum Role {
		user,
		admin
	}

	@Bean
	public UserPostService userPostService(PostRepository repository) {
		return new UserPostServiceImpl(repository);
	}

	@Bean
	public AdminPostService adminPostService(PostRepository repository) {
		return new AdminPostServiceImpl(repository);
	}

	@Bean
	public Map<Role, PostDelectionService> postDelectionServices(PostRepository repository) {
		return Map.of(
				Role.admin, new AdminPostServiceImpl(repository),
				Role.user, new UserPostServiceImpl(repository));
	}

	@Bean
	public Map<Role, TrendingPostService> trendingPostServices(PostRepository repository) {
		return Map.of(
				Role.admin, new AdminPostServiceImpl(repository),
				Role.user, new UserPostServiceImpl(repository));
	}

}
