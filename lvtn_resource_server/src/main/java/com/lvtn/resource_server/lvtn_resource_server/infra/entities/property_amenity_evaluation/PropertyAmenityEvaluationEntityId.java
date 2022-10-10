package com.lvtn.resource_server.lvtn_resource_server.infra.entities.property_amenity_evaluation;

import java.io.Serializable;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.PropertyAmenityEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.ServiceEvaluationPostEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyAmenityEvaluationEntityId implements Serializable {
	private ServiceEvaluationPostEntity serviceEvaluation;

	private PropertyAmenityEntity propertyAmenity;
}
