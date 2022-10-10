package com.lvtn.resource_server.lvtn_resource_server.infra.entities.property_amenity_evaluation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.PropertyAmenityEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.ServiceEvaluationPostEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "property_amenities_evaluation")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@IdClass(PropertyAmenityEvaluationEntityId.class)
public class PropertyAmenityEvaluationEntity {
	@Id
	@ManyToOne
	private ServiceEvaluationPostEntity serviceEvaluation;

	@Id
	@ManyToOne
	private PropertyAmenityEntity propertyAmenity;

	@NotNull
	private float score;

	private String note;
}
