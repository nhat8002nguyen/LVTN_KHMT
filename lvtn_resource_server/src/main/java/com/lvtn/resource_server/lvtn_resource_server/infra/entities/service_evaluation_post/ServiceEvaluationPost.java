package com.lvtn.resource_server.lvtn_resource_server.infra.entities.service_evaluation_post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.Hotel;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.User;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "service_evaluation_posts")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ServiceEvaluationPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private User user;

	@Size(max = 500, message = "The title of the post must be at most 500 chracter long")
	private String title;

	@NotNull
	@NotBlank
	@Size(max = 10000, message = "The description of the post must be at most 10000 character long")
	private String body;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	private List<PostImage> postImages = new ArrayList<>();

	@NotNull
	@ManyToOne
	private Hotel hotel;

	private float locationRating;

	private float cleanlinessRating;

	private float serviceRating;

	private float valueRating;

	private int likedCount;

	private int dislikedCount;

	private int sharedCount;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;
}
