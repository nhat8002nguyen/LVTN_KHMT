package com.lvtn.resource_server.lvtn_resource_server.apis.v1.dto.requests.post_request;

import lombok.Data;

@Data
public class PropertyAmenityEvaluationDto {
	private long propertyAmenityId;
	private double score;
	private String note;
}
