package com.lvtn.resource_server.lvtn_resource_server.infra.entities.service_evaluation_post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostImage {
	private long id;

	private long postId;

	private String url;
}
