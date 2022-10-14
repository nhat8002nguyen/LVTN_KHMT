package com.lvtn.resource_server.lvtn_resource_server.domains.posts.services;

import java.util.List;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.Hotel;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.PostImage;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost.PostVisibility;

public interface UserPostService {
	ServiceEvaluationPost createPost(ServiceEvaluationPost post);

	ServiceEvaluationPost updatePost(long postId, ServiceEvaluationPost post);

	List<ServiceEvaluationPost> getNewFeeds(long userId);

	List<ServiceEvaluationPost> getPublicPostsOfUser(long userId, int size, int page);

	List<Hotel> getHotels();

	ServiceEvaluationPost updatePostTitle(long id, String title);

	ServiceEvaluationPost updatePostBody(long id, String body);

	ServiceEvaluationPost updatePostImages(long id, List<PostImage> images);

	ServiceEvaluationPost updatePostHotel(long id, Hotel hotel);

	ServiceEvaluationPost updatePostCleanlinessRating(long id, double cleanlinessRating);

	ServiceEvaluationPost updatePostServiceRating(long id, double serviceRating);

	ServiceEvaluationPost updatePostLocationRating(long id, double valueRating);

	ServiceEvaluationPost updatePostLikedCount(long id, int likedCount);

	ServiceEvaluationPost updatePostDislikedCount(long id, int dislikedCount);

	ServiceEvaluationPost updatePostSharedCount(long id, int sharedCount);

	ServiceEvaluationPost updatePostVisibility(long id, PostVisibility visibility);
}
