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
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.property_amenity_evaluation.PropertyAmenityEvaluationEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.room_feature_evaluation.RoomFeatureEvaluationEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.mappers.post.PostMappers;
import com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.property_amenity_evaluation.JPAPropertyAmenityEvaluationRepository;
import com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.room_feature_evaluation.JPARoomFeatureEvaluationRepository;

@Component
public class PostRepositoryImpl implements PostRepository {

	private JPAPostRepository repository;
	private PostMappers postMapper;
	private JPAPropertyAmenityEvaluationRepository propertyAmenityEvaluationRepository;
	private JPARoomFeatureEvaluationRepository roomFeatureEvaluationRepository;

	@Autowired
	public PostRepositoryImpl(
			JPAPostRepository repository,
			JPAPropertyAmenityEvaluationRepository propertyAmenityEvaluationRepository,
			JPARoomFeatureEvaluationRepository roomFeatureEvaluationRepository) {
		this.repository = repository;
		this.postMapper = PostMappers.INSTANCE;
		this.propertyAmenityEvaluationRepository = propertyAmenityEvaluationRepository;
		this.roomFeatureEvaluationRepository = roomFeatureEvaluationRepository;
	}

	@Override
	public ServiceEvaluationPost createPost(ServiceEvaluationPost post) {
		ServiceEvaluationPostEntity entity = postMapper.serviceEvaluationPostToEntity(post);
		ServiceEvaluationPostEntity savedPostEntity = repository.save(entity);

		List<PropertyAmenityEvaluationEntity> propertyAmenityEvaluations = post.getPropertyAmenityEvaluations().stream()
				.map(
						item -> postMapper.propertyAmenityEvaluationPojoToEntity(post, item))
				.collect(Collectors.toList());

		List<RoomFeatureEvaluationEntity> roomFeatureEvaluations = post.getRoomFeatureEvaluations().stream()
				.map(
						item -> postMapper.roomFeatureEvaluationToEntity(item, post))
				.collect(Collectors.toList());

		propertyAmenityEvaluationRepository.saveAll(propertyAmenityEvaluations);
		roomFeatureEvaluationRepository.saveAll(roomFeatureEvaluations);

		return postMapper.serviceEvaluationPostEntityToPojo(
				savedPostEntity, propertyAmenityEvaluations, roomFeatureEvaluations);
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

		List<PropertyAmenityEvaluationEntity> propertyAmenityEvaluations = post.getPropertyAmenityEvaluations().stream()
				.map(
						item -> postMapper.propertyAmenityEvaluationPojoToEntity(post, item))
				.collect(Collectors.toList());

		List<RoomFeatureEvaluationEntity> roomFeatureEvaluations = post.getRoomFeatureEvaluations().stream()
				.map(
						item -> postMapper.roomFeatureEvaluationToEntity(item, post))
				.collect(Collectors.toList());

		propertyAmenityEvaluationRepository.saveAll(propertyAmenityEvaluations);
		roomFeatureEvaluationRepository.saveAll(roomFeatureEvaluations);

		ServiceEvaluationPost result = postMapper.serviceEvaluationPostEntityToPojo(
				entity,
				propertyAmenityEvaluations,
				roomFeatureEvaluations);
		return result;
	}

	@Override
	public List<ServiceEvaluationPost> findRecentPosts(int size, int page) {
		Pageable pageable = PageRequest.of(page, size);

		List<ServiceEvaluationPostEntity> entites = repository.findByOrderByCreatedAtDesc(pageable);

		return getServiceEvaluationPostPojosFromEntities(entites);
	}

	@Override
	public ServiceEvaluationPost findPost(long postId) {
		Optional<ServiceEvaluationPostEntity> optional = repository.findById(postId);

		if (optional.isEmpty()) {
			return null;
		}

		ServiceEvaluationPostEntity entity = optional.get();

		return getServiceEvaluationPostPojoFromEntity(entity);
	}

	@Override
	public List<ServiceEvaluationPost> findPostsByUserId(long userId, int size, int page) {
		Pageable pageable = PageRequest.of(page, size);
		List<ServiceEvaluationPostEntity> entities = repository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
		return getServiceEvaluationPostPojosFromEntities(entities);
	}

	private List<ServiceEvaluationPost> getServiceEvaluationPostPojosFromEntities(
			List<ServiceEvaluationPostEntity> entities) {
		return entities.stream().map(e -> getServiceEvaluationPostPojoFromEntity(e)).collect(Collectors.toList());
	}

	private ServiceEvaluationPost getServiceEvaluationPostPojoFromEntity(ServiceEvaluationPostEntity entity) {
		List<PropertyAmenityEvaluationEntity> propertyAmenityEvaluations = propertyAmenityEvaluationRepository
				.findByServiceEvaluationId(entity.getId());
		List<RoomFeatureEvaluationEntity> roomFeatureEvaluations = roomFeatureEvaluationRepository
				.findByServiceEvaluationId(entity.getId());

		return postMapper.serviceEvaluationPostEntityToPojo(entity, propertyAmenityEvaluations, roomFeatureEvaluations);
	}

	@Override
	public List<ServiceEvaluationPost> findRecentPosts() {
		return findRecentPosts(10, 1);
	}

}
