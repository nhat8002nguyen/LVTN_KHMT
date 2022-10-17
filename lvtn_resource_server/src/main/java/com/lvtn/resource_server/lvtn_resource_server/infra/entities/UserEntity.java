package com.lvtn.resource_server.lvtn_resource_server.infra.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@NotBlank
	private String username;

	@NotNull
	@NotBlank
	@Size(max = 50, message = "First name should has at most 50 charactor long")
	private String firstName;

	@Size(max = 50, message = "First name should has at most 50 charactor long")
	@NotNull
	@NotBlank
	private String lastName;

	@NotNull
	private String email;

	@NotNull
	private String password;

	private String phone;

	@Size(max = 10000, message = "User's About must be at most 10000 character long")
	private String about;

	private String imageUrl;

	private final String role;

	@OneToMany(mappedBy = "user")
	private List<ServiceEvaluationPostEntity> serviceEvaluationPosts;

	@ManyToMany
	private List<UserEntity> friends;
}
