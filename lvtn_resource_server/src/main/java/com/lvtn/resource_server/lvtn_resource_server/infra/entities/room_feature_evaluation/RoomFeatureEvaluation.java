package com.lvtn.resource_server.lvtn_resource_server.infra.entities.room_feature_evaluation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.RoomFeature;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.service_evaluation_post.ServiceEvaluationPost;

import lombok.Data;

@Entity(name = "room_features_evaluation")
@Data
@IdClass(RoomFeatureEvaluationId.class)
public class RoomFeatureEvaluation {
	@Id
	@ManyToOne
	private ServiceEvaluationPost serviceEvaluation;

	@Id
	@ManyToOne
	private RoomFeature roomFeature;

	@NotNull
	private float score;
}
