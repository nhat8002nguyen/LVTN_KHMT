package com.lvtn.resource_server.lvtn_resource_server.apis.v1.dto.requests.post_request;

import lombok.Data;

@Data
public class RoomFeatureEvaluationDto {
	private long roomFeatuereId;
	private double score;
	private String note;
}
