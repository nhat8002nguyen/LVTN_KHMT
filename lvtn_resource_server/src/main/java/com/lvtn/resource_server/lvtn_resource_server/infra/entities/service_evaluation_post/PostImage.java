package com.lvtn.resource_server.lvtn_resource_server.infra.entities.service_evaluation_post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "post_images")
@Data
@NoArgsConstructor
public class PostImage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private ServiceEvaluationPost post;

	@NotNull
	private String url;
}
