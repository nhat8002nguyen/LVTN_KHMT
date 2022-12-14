package com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.post_repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.exceptions.PostNotFoundException;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.Hotel;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.PostImage;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost.PostVisibility;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.repositories.post_repository.PostRepository;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.HotelEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.PostImageEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.ServiceEvaluationPostEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.mappers.post.PostMappers;
import com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.hotel_repository.JPAHotelRepository;

@Component
public class PostRepositoryImpl implements PostRepository {

	private JPAPostRepository repository;
	private JPAHotelRepository hotelRepository;
	private PostMappers postMapper;

	@Autowired
	public PostRepositoryImpl(
			JPAPostRepository repository,
			JPAHotelRepository hotelRepository) {
		this.repository = repository;
		this.postMapper = PostMappers.INSTANCE;
		this.hotelRepository = hotelRepository;
	}

	@Override
	public ServiceEvaluationPost createPost(ServiceEvaluationPost post) {
		ServiceEvaluationPostEntity entity = postMapper.serviceEvaluationPostToEntity(post);
		ServiceEvaluationPostEntity savedPostEntity = repository.save(entity);

		return postMapper.serviceEvaluationPostEntityToPojo(
				savedPostEntity);
	}

	private ServiceEvaluationPostEntity getPostEntityOrThrowException(long id) {
		Optional<ServiceEvaluationPostEntity> optional = repository.findById(id);

		if (optional.isEmpty()) {
			throw new PostNotFoundException(id);
		}

		return optional.get();
	}

	@Override
	public void deletePost(long postId) {
		getPostEntityOrThrowException(postId);
		repository.deleteById(postId);
	}

	@Override
	public ServiceEvaluationPost updatePost(long postId, ServiceEvaluationPost post) {
		getPostEntityOrThrowException(postId);

		ServiceEvaluationPostEntity entity = postMapper.serviceEvaluationPostToEntity(post);
		entity.setId(postId);
		entity = repository.save(entity);

		ServiceEvaluationPost result = postMapper.serviceEvaluationPostEntityToPojo(
				entity);
		return result;
	}

	@Override
	public List<ServiceEvaluationPost> findHomePosts(String username, int page, int size) {
		List<ServiceEvaluationPostEntity> entities = repository.findByUserFriendsUsernameOrderByCreatedAtDesc(username);

		List<ServiceEvaluationPostEntity> subEntities = findSubEntities(entities, page, size);

		return subEntities.stream().map(postMapper::serviceEvaluationPostEntityToPojo).collect(Collectors.toList());
	}

	private <T> List<T> findSubEntities(List<T> entities, int page, int size) {
		int entitiesCount = entities.size();

		int pageCount = entitiesCount % size == 0 ? entitiesCount / size : entitiesCount / size + 1;

		List<T> subEntities = new ArrayList<>();

		if (page < pageCount) {
			int start = page * size;
			int end = page == pageCount - 1 ? entitiesCount - 1 : ((page + 1) * size) - 1;
			subEntities = entities.subList(start, end + 1);
		}

		return subEntities;
	}

	@Override
	public ServiceEvaluationPost findPost(long postId) {

		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(postId);

		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public List<ServiceEvaluationPost> findPostsByUserId(long userId, int size, int page) {
		Pageable pageable = PageRequest.of(page, size);
		List<ServiceEvaluationPostEntity> entities = repository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
		return entities.stream().map(postMapper::serviceEvaluationPostEntityToPojo).collect(Collectors.toList());
	}

	@Override
	public List<ServiceEvaluationPost> findPostsByUsername(String username, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<ServiceEvaluationPostEntity> entities = repository.findByUserUsernameOrderByCreatedAtDesc(username, pageable);
		return entities.stream().map(postMapper::serviceEvaluationPostEntityToPojo).collect(Collectors.toList());
	}

	@Override
	public List<ServiceEvaluationPost> findHomePosts(String username) {
		return findHomePosts(username, 0, 10);
	}

	@Override
	public List<Hotel> findAllHotels() {
		Iterable<HotelEntity> iterable = hotelRepository.findAll();
		List<Hotel> hotelPojos = new ArrayList<>();
		iterable.forEach(i -> hotelPojos.add(postMapper.hotelEntityToHotel(i)));
		return hotelPojos;
	}

	@Override
	public ServiceEvaluationPost updatePostTitle(long id, String title) {
		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(id);
		entity.setTitle(title);
		repository.save(entity);
		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public ServiceEvaluationPost updatePostBody(long id, String body) {
		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(id);
		entity.setBody(body);
		repository.save(entity);
		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public ServiceEvaluationPost updatePostImages(long id, List<PostImage> images) {
		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(id);
		List<PostImageEntity> imageEntities = images.stream().map(image -> postMapper.postImageToPostImageEntity(image))
				.collect(Collectors.toList());
		entity.setPostImages(imageEntities);
		repository.save(entity);
		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public ServiceEvaluationPost updatePostHotel(long id, Hotel hotel) {
		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(id);
		entity.setHotel(postMapper.hotelToHotelEntity(hotel));
		repository.save(entity);
		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public ServiceEvaluationPost updatePostCleanlinessRating(long id, double cleanlinessRating) {
		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(id);
		entity.setCleanlinessRating(cleanlinessRating);
		repository.save(entity);
		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public ServiceEvaluationPost updatePostServiceRating(long id, double serviceRating) {
		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(id);
		entity.setServiceRating(serviceRating);
		repository.save(entity);
		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public ServiceEvaluationPost updatePostLocationRating(long id, double valueRating) {
		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(id);
		entity.setValueRating(valueRating);
		repository.save(entity);
		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public ServiceEvaluationPost updatePostLikedCount(long id, int likedCount) {
		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(id);
		entity.setLikedCount(likedCount);
		repository.save(entity);
		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public ServiceEvaluationPost updatePostDislikedCount(long id, int dislikedCount) {
		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(id);
		entity.setDislikedCount(dislikedCount);
		repository.save(entity);
		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public ServiceEvaluationPost updatePostSharedCount(long id, int sharedCount) {
		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(id);
		entity.setSharedCount(sharedCount);
		repository.save(entity);
		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public ServiceEvaluationPost updatePostVisibility(long id, PostVisibility visibility) {
		ServiceEvaluationPostEntity entity = getPostEntityOrThrowException(id);
		entity.setVisibility(visibility);
		repository.save(entity);
		return postMapper.serviceEvaluationPostEntityToPojo(entity);
	}

	@Override
	public int findHomePostsSize(String username) {
		return repository.findByUserFriendsUsernameOrderByCreatedAtDesc(username).size();
	}

	@Override
	public List<ServiceEvaluationPost> findRecentPosts(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		List<ServiceEvaluationPostEntity> entities = repository.findByOrderByCreatedAtDesc(pageable);

		return entities.stream().map(postMapper::serviceEvaluationPostEntityToPojo).collect(Collectors.toList());
	}
}
