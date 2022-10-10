package com.lvtn.resource_server.lvtn_resource_server.infra.entities.room_feature_evaluation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.RoomFeatureEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.ServiceEvaluationPostEntity;

import lombok.Data;

@Entity(name = "room_features_evaluation")
@Data
@IdClass(RoomFeatureEvaluationEntityId.class)
public class RoomFeatureEvaluationEntity {
	@Id
	@ManyToOne
	private ServiceEvaluationPostEntity serviceEvaluation;

	@Id
	@ManyToOne
	private RoomFeatureEntity roomFeature;

	@NotNull
	private float score;

	private String note;
}
