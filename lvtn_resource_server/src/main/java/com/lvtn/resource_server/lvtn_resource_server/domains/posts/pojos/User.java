package com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User {
	private long id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String phone;

	private String about;

	private String imageUrl;

}
