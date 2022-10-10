package com.lvtn.resource_server.lvtn_resource_server.domains.posts.services;

import java.util.List;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;

public interface AdminPostService {
	List<ServiceEvaluationPost> getUserPosts(long userId, int page, int size);

	List<ServiceEvaluationPost> getRecentPosts(int page, int size);
}
