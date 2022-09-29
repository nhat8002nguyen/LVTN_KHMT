package com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class RoomFeature {
	private long id;

	private String icon;

	private String description;
}
