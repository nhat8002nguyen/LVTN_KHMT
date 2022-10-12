package com.lvtn.resource_server.lvtn_resource_server.apis.v1.dto.requests.post_request;

import java.util.List;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost.PostVisibility;

import lombok.Data;

@Data
public class PostRequestDto {
	private String title;
	private String body;
	private List<PostImageDto> postImages;
	private long hotelId;
	private double locationRating;
	private double cleanlinessRating;
	private double serviceRating;
	private double valueRating;
	private int likedCount;
	private int dislikedCount;
	private int sharedCount;
	private List<PropertyAmenityEvaluationDto> propertyAmenityEvaluationDto;
	private List<RoomFeatureEvaluationDto> roomFeatureEvaluationDto;
	private PostVisibility visibility;

}
