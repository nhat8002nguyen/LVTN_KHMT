package com.lvtn.resource_server.lvtn_resource_server.infra.entities.room_feature_evaluation;

import java.io.Serializable;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.RoomFeature;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.service_evaluation_post.ServiceEvaluationPost;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomFeatureEvaluationId implements Serializable {
	private ServiceEvaluationPost serviceEvaluation;

	private RoomFeature roomFeature;
}
