package com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos;

import lombok.Data;

@Data
public class Hotel {
	private long id;

	private String name;

	private String location;

	private String about;

	private float rating;
}
