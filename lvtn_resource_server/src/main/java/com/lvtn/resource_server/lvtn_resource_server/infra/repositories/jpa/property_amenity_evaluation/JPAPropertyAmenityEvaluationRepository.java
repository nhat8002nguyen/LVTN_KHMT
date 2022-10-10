package com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.property_amenity_evaluation;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.property_amenity_evaluation.PropertyAmenityEvaluationEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.property_amenity_evaluation.PropertyAmenityEvaluationEntityId;

public interface JPAPropertyAmenityEvaluationRepository
		extends CrudRepository<PropertyAmenityEvaluationEntity, PropertyAmenityEvaluationEntityId> {
	List<PropertyAmenityEvaluationEntity> findByServiceEvaluationId(long evaluationId);
}
