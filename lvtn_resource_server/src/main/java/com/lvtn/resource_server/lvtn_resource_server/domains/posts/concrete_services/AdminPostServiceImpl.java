package com.lvtn.resource_server.lvtn_resource_server.domains.posts.concrete_services;

import java.util.List;
import java.util.stream.Collectors;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.repositories.post_repository.PostRepository;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.AdminPostService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.PostDelectionService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.TrendingPostService;

public class AdminPostServiceImpl implements AdminPostService, PostDelectionService, TrendingPostService {
	private PostRepository postRepository;

	private static final int TRENDING_POST_MAX = 100;

	public AdminPostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public List<ServiceEvaluationPost> getTop100TrendingPosts() {
		List<ServiceEvaluationPost> recentPosts = postRepository.findRecentPosts(TRENDING_POST_MAX, 1);

		return getTrendingPostsFromRecentPosts(recentPosts);
	}

	private List<ServiceEvaluationPost> getTrendingPostsFromRecentPosts(List<ServiceEvaluationPost> recentPosts) {
		// Trending posts should have at least 1000 likeds and 100 shareds
		return recentPosts.stream()
				.filter(p -> p.getLikedCount() >= 500 && p.getSharedCount() >= 100)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean deletePost(long postId) {
		postRepository.deletePost(postId);

		return true;
	}

	@Override
	public List<ServiceEvaluationPost> getUserPosts(long userId, int page, int size) {
		return postRepository.findPostsByUserId(userId, size, page);
	}

	@Override
	public List<ServiceEvaluationPost> getRecentPosts(int page, int size) {
		return postRepository.findRecentPosts(size, page);
	}
}
