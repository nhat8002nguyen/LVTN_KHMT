package com.lvtn.resource_server.lvtn_resource_server.domains.posts.concrete_services;

import java.util.List;
import java.util.stream.Collectors;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.exceptions.PostNotFoundException;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost.PostVisibility;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.repositories.post_repository.PostRepository;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.PostDelectionService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.TrendingPostService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.UserPostService;

public class UserPostServiceImpl implements UserPostService, PostDelectionService, TrendingPostService {

	private PostRepository postRepository;

	private static final int TRENDING_POST_MAX = 100;

	public UserPostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public List<ServiceEvaluationPost> getTop100TrendingPosts() {
		List<ServiceEvaluationPost> recentPosts = postRepository.findRecentPosts(TRENDING_POST_MAX, 1);
		return getTrendingPostsFromRecentPosts(recentPosts);
	}

	private List<ServiceEvaluationPost> getTrendingPostsFromRecentPosts(List<ServiceEvaluationPost> recentPosts) {
		// Trending posts should have at least 1000 likeds and 100 shareds
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
		ServiceEvaluationPost targetPost = postRepository.findPost(postId);

		if (targetPost == null) {
			throw new PostNotFoundException();
		}

		ServiceEvaluationPost updatedPost = postRepository.updatePost(postId, targetPost);

		return updatedPost;
	}

	@Override
	public List<ServiceEvaluationPost> getNewFeeds(long userId) {
		return postRepository.findPostsByUserId(userId, 10, 0);
	}

	@Override
	public List<ServiceEvaluationPost> getPublicPostsOfUser(long userId, int size, int page) {
		List<ServiceEvaluationPost> posts = postRepository.findPostsByUserId(userId, size, page);
		return posts.stream()
				.filter(p -> p.getVisibility() == PostVisibility.PUBLIC)
				.collect(Collectors.toList());
	}

}
