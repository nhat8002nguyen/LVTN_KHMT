package com.lvtn.resource_server.lvtn_resource_server.infra.entities.property_amenity_evaluation;

import java.io.Serializable;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.PropertyAmenity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.service_evaluation_post.ServiceEvaluationPost;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertyAmenityEvaluationId implements Serializable {
	private ServiceEvaluationPost serviceEvaluation;

	private PropertyAmenity propertyAmenity;
}
