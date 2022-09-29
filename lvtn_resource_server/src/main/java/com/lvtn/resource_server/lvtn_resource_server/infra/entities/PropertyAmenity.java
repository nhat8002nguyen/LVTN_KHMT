package com.lvtn.resource_server.lvtn_resource_server.infra.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "property_amenities")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PropertyAmenity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String icon;

	@NotNull
	@NotBlank
	private String description;
}
