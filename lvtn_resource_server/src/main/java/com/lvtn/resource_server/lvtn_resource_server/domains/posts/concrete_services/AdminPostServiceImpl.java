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

	public AdminPostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public List<ServiceEvaluationPost> getTop10TrendingPosts() {
		List<ServiceEvaluationPost> recentPosts = postRepository.findRecentPosts(0, 10);

		return getTrendingPostsFromRecentPosts(recentPosts).subList(0, 10);
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
}
