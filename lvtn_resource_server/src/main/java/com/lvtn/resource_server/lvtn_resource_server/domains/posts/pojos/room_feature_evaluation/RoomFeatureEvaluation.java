package com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.room_feature_evaluation;

import lombok.Data;

@Data
public class RoomFeatureEvaluation {
	private Long serviceEvaluationId;

	private Long roomFeatureId;

	private float score;
}
