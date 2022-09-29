package com.lvtn.resource_server.lvtn_resource_server.infra.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "room_features")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class RoomFeature {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String icon;

	@NotNull
	private String description;
}
