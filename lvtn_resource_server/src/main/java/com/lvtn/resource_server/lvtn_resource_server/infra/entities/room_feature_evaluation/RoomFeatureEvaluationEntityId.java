package com.lvtn.resource_server.lvtn_resource_server.infra.entities.room_feature_evaluation;

import java.io.Serializable;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.RoomFeatureEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.ServiceEvaluationPostEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomFeatureEvaluationEntityId implements Serializable {
	private ServiceEvaluationPostEntity serviceEvaluation;

	private RoomFeatureEntity roomFeature;
}
