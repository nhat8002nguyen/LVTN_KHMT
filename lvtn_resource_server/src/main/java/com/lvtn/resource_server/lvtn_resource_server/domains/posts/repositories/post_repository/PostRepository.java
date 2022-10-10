package com.lvtn.resource_server.lvtn_resource_server.domains.posts.repositories.post_repository;

import java.util.List;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;

public interface PostRepository {
	ServiceEvaluationPost createPost(ServiceEvaluationPost post);

	void deletePost(long postId);

	ServiceEvaluationPost updatePost(long postId, ServiceEvaluationPost post);

	List<ServiceEvaluationPost> findRecentPosts();

	List<ServiceEvaluationPost> findRecentPosts(int size, int page);

	ServiceEvaluationPost findPost(long postId);

	List<ServiceEvaluationPost> findPostsByUserId(long userId, int size, int page);
}
