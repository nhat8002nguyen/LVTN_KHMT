package com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos;

import lombok.Data;

@Data
public class RoomFeatureEvaluation {
	private RoomFeature roomFeature;

	private float score;

	private String note;
}
