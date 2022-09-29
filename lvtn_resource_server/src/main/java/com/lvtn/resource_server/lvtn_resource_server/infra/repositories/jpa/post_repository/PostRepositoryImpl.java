package com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.post_repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.repositories.post_repository.PostRepository;

public class PostRepositoryImpl implements PostRepository {

	private JPAPostRepository repository;

	@Autowired
	public PostRepositoryImpl(JPAPostRepository repository) {
		this.repository = repository;
	}

}
