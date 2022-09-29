package com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.property_amenity_evaluation;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PropertyAmenityEvaluation {
	private Long serviceEvaluationId;

	private Long propertyAmenityId;

	private float score;
}
