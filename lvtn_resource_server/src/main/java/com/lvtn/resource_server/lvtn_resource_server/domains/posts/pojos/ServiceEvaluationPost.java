package com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ServiceEvaluationPost {
	private long id;

	private User user;

	private String title;

	private String body;

	private List<PostImage> postImages;

	private Hotel hotel;

	private double locationRating;

	private double cleanlinessRating;

	private double serviceRating;

	private double valueRating;

	private int likedCount;

	private int dislikedCount;

	private int sharedCount;

	private PostVisibility visibility;

	private Date createdAt;

	private Date updatedAt;

	public enum PostVisibility {
		PRIVATE,
		PUBLIC
	}
}
