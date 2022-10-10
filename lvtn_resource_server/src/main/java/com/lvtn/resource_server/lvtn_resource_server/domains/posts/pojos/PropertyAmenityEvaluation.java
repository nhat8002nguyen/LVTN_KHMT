package com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos;

import lombok.Data;

@Data
public class PropertyAmenityEvaluation {
	private PropertyAmenity propertyAmenity;

	private float score;

	private String note;
}
