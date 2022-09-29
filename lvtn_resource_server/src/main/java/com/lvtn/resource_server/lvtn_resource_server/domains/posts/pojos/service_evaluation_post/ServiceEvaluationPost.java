package com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.service_evaluation_post;

import java.util.Date;
import java.util.List;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.User;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.Hotel;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ServiceEvaluationPost {
	private long id;

	private User user;

	private String title;

	private String body;

	private List<PostImage> postImages;

	private Hotel hotel;

	private float locationRating;

	private float cleanlinessRating;

	private float serviceRating;

	private float valueRating;

	private int likedCount;

	private int dislikedCount;

	private int sharedCount;

	private Date createdAt;

	private Date updatedAt;
}
