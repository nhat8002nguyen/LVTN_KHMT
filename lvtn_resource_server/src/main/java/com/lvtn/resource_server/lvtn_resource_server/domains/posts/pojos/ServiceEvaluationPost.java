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

	private float locationRating;

	private float cleanlinessRating;

	private float serviceRating;

	private float valueRating;

	private int likedCount;

	private int dislikedCount;

	private int sharedCount;

	private List<PropertyAmenityEvaluation> propertyAmenityEvaluations;

	private List<RoomFeatureEvaluation> roomFeatureEvaluations;

	private PostVisibility visibility;

	private Date createdAt;

	private Date updatedAt;

	public enum PostVisibility {
		PRIVATE,
		PUBLIC
	}
}
