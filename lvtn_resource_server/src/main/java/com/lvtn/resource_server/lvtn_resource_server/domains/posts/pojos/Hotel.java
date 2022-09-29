package com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Hotel {
	private long id;

	private String name;

	private String location;

	private String about;

	private float rating;
}
