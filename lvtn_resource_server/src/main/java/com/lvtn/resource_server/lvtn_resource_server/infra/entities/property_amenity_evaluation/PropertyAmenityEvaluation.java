package com.lvtn.resource_server.lvtn_resource_server.infra.entities.property_amenity_evaluation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.PropertyAmenity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.service_evaluation_post.ServiceEvaluationPost;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "property_amenities_evaluation")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@IdClass(PropertyAmenityEvaluationId.class)
public class PropertyAmenityEvaluation {
	@Id
	@ManyToOne
	private ServiceEvaluationPost serviceEvaluation;

	@Id
	@ManyToOne
	private PropertyAmenity propertyAmenity;

	@NotNull
	private float score;
}
