package com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos;

import lombok.Data;

@Data
public class User {
	private long id;

	private String username;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String phone;

	private String about;

	private String imageUrl;

}
