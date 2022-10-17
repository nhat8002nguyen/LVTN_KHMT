package com.lvtn.resource_server.lvtn_resource_server.domains.posts.concrete_services;

import java.util.List;
import java.util.stream.Collectors;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.Hotel;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.PostImage;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost.PostVisibility;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.repositories.post_repository.PostRepository;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.PostDelectionService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.TrendingPostService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.UserPostService;

public class UserPostServiceImpl implements UserPostService, PostDelectionService, TrendingPostService {

	private PostRepository postRepository;

	public UserPostServiceImpl(
			PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public List<ServiceEvaluationPost> getTop10TrendingPosts() {
		List<ServiceEvaluationPost> recentPosts = postRepository.findRecentPosts(0, 100);
		List<ServiceEvaluationPost> trendablePosts = getTrendingPostsFromRecentPosts(recentPosts);
		return trendablePosts.subList(0, 10);
	}

	private List<ServiceEvaluationPost> getTrendingPostsFromRecentPosts(List<ServiceEvaluationPost> recentPosts) {
		// Trending posts should have at least 500 likeds and 100 shareds
		List<ServiceEvaluationPost> postsWith1000Likes = recentPosts.stream()
				.filter(p -> p.getLikedCount() >= 500 && p.getSharedCount() >= 100)
				.collect(Collectors.toList());

		return postsWith1000Likes;
	}

	@Override
	public Boolean deletePost(long postId) {
		ServiceEvaluationPost post = postRepository.findPost(postId);

		if (post == null) {
			return false;
		}

		postRepository.deletePost(postId);

		return true;
	}

	@Override
	public ServiceEvaluationPost createPost(ServiceEvaluationPost post) {
		ServiceEvaluationPost createdPost = postRepository.createPost(post);
		return createdPost;
	}

	@Override
	public ServiceEvaluationPost updatePost(long postId, ServiceEvaluationPost post) {
		return postRepository.updatePost(postId, post);
	}

	@Override
	public List<ServiceEvaluationPost> getNewFeeds(String username, int page, int size) {
		return postRepository.findHomePosts(username, page, size);
	}

	@Override
	public int getNewFeedsSize(String username) {
		return postRepository.findHomePostsSize(username);
	}

	@Override
	public List<ServiceEvaluationPost> getPublicPostsOfUser(long userId, int size, int page) {
		List<ServiceEvaluationPost> posts = postRepository.findPostsByUserId(userId, size, page);
		return posts.stream()
				.filter(p -> p.getVisibility() == PostVisibility.PUBLIC)
				.collect(Collectors.toList());
	}

	@Override
	public List<Hotel> getHotels() {
		return postRepository.findAllHotels();
	}

	@Override
	public ServiceEvaluationPost updatePostTitle(long id, String title) {
		return postRepository.updatePostTitle(id, title);
	}

	@Override
	public ServiceEvaluationPost updatePostBody(long id, String body) {
		return postRepository.updatePostBody(id, body);
	}

	@Override
	public ServiceEvaluationPost updatePostImages(long id, List<PostImage> images) {
		return postRepository.updatePostImages(id, images);
	}

	@Override
	public ServiceEvaluationPost updatePostHotel(long id, Hotel hotel) {
		return postRepository.updatePostHotel(id, hotel);
	}

	@Override
	public ServiceEvaluationPost updatePostCleanlinessRating(long id, double cleanlinessRating) {
		return postRepository.updatePostCleanlinessRating(id, cleanlinessRating);
	}

	@Override
	public ServiceEvaluationPost updatePostServiceRating(long id, double serviceRating) {
		return postRepository.updatePostServiceRating(id, serviceRating);
	}

	@Override
	public ServiceEvaluationPost updatePostLocationRating(long id, double valueRating) {
		return postRepository.updatePostLocationRating(id, valueRating);
	}

	@Override
	public ServiceEvaluationPost updatePostLikedCount(long id, int likedCount) {
		return postRepository.updatePostLikedCount(id, likedCount);
	}

	@Override
	public ServiceEvaluationPost updatePostDislikedCount(long id, int dislikedCount) {
		return updatePostDislikedCount(id, dislikedCount);
	}

	@Override
	public ServiceEvaluationPost updatePostSharedCount(long id, int sharedCount) {
		return postRepository.updatePostSharedCount(id, sharedCount);
	}

	@Override
	public ServiceEvaluationPost updatePostVisibility(long id, PostVisibility visibility) {
		return postRepository.updatePostVisibility(id, visibility);
	}

}
