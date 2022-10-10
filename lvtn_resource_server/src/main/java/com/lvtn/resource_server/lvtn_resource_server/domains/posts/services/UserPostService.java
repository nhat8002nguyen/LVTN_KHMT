package com.lvtn.resource_server.lvtn_resource_server.domains.posts.services;

import java.util.List;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;

public interface UserPostService {
	ServiceEvaluationPost createPost(ServiceEvaluationPost post);

	ServiceEvaluationPost updatePost(long postId, ServiceEvaluationPost post);

	List<ServiceEvaluationPost> getNewFeeds(long userId);

	List<ServiceEvaluationPost> getPublicPostsOfUser(long userId, int size, int page);
}
