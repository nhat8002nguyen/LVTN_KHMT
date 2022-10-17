package com.lvtn.resource_server.lvtn_resource_server.infra.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost.PostVisibility;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "service_evaluation_posts")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class ServiceEvaluationPostEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private UserEntity user;

	@Size(max = 500, message = "The title of the post must be at most 500 chracter long")
	private String title;

	@NotNull
	@NotBlank
	@Size(max = 10000, message = "The description of the post must be at most 10000 character long")
	private String body;

	@OneToMany(mappedBy = "serviceEvaluationPost", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<PostImageEntity> postImages;

	@ManyToOne
	private HotelEntity hotel;

	private double locationRating;

	private double cleanlinessRating;

	private double serviceRating;

	private double valueRating;

	private int likedCount;

	private int dislikedCount;

	private int sharedCount;

	@Enumerated(EnumType.STRING)
	private PostVisibility visibility;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;
}
