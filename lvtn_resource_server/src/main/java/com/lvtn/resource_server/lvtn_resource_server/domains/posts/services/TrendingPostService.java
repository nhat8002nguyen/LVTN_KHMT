package com.lvtn.resource_server.lvtn_resource_server.domains.posts.services;

import java.util.List;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;

public interface TrendingPostService {
	List<ServiceEvaluationPost> getTop100TrendingPosts();
}
