package com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.post_repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.repositories.post_repository.PostRepository;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.ServiceEvaluationPostEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.mappers.post.PostMappers;

@Component
public class PostRepositoryImpl implements PostRepository {

	private JPAPostRepository repository;
	private PostMappers postMapper;

	@Autowired
	public PostRepositoryImpl(
			JPAPostRepository repository) {
		this.repository = repository;
		this.postMapper = PostMappers.INSTANCE;
	}

	@Override
	public ServiceEvaluationPost createPost(ServiceEvaluationPost post) {
		ServiceEvaluationPostEntity entity = postMapper.serviceEvaluationPostToEntity(post);
		ServiceEvaluationPostEntity savedPostEntity = repository.save(entity);

		return postMapper.serviceEvaluationPostEntityToPojo(
				savedPostEntity);
	}

	@Override
	public void deletePost(long postId) {
		repository.deleteById(postId);
	}

	@Override
	public ServiceEvaluationPost updatePost(long postId, ServiceEvaluationPost post) {
		Optional<ServiceEvaluationPostEntity> optional = repository.findById(postId);

		if (optional.isEmpty()) {
			return null;
		}

		ServiceEvaluationPostEntity entity = postMapper.serviceEvaluationPostToEntity(post);
		entity.setId(postId);
		entity = repository.save(entity);

		ServiceEvaluationPost result = postMapper.serviceEvaluationPostEntityToPojo(
				entity);
		return result;
	}

	@Override
	public List<ServiceEvaluationPost> findRecentPosts(int size, int page) {
		Pageable pageable = PageRequest.of(page, size);

		List<ServiceEvaluationPostEntity> entities = repository.findByOrderByCreatedAtDesc(pageable);

		return entities.stream().map(postMapper::serviceEvaluationPostEntityToPojo).collect(Collectors.toList());
	}

	@Override
	public ServiceEvaluationPost findPost(long postId) {
		Optional<ServiceEvaluationPostEntity> optional = repository.findById(postId);

		if (optional.isEmpty()) {
			return null;
		}

		ServiceEvaluationPostEntity entity = optional.get();

		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public List<ServiceEvaluationPost> findPostsByUserId(long userId, int size, int page) {
		Pageable pageable = PageRequest.of(page, size);
		List<ServiceEvaluationPostEntity> entities = repository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
		return entities.stream().map(postMapper::serviceEvaluationPostEntityToPojo).collect(Collectors.toList());
	}

	@Override
	public List<ServiceEvaluationPost> findRecentPosts() {
		return findRecentPosts(10, 1);
	}

}
