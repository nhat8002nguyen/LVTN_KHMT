package com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.room_feature_evaluation;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.room_feature_evaluation.RoomFeatureEvaluationEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.room_feature_evaluation.RoomFeatureEvaluationEntityId;

public interface JPARoomFeatureEvaluationRepository
		extends CrudRepository<RoomFeatureEvaluationEntity, RoomFeatureEvaluationEntityId> {
	List<RoomFeatureEvaluationEntity> findByServiceEvaluationId(long evaluationId);
}
