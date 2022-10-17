package com.lvtn.resource_server.lvtn_resource_server.apis.v1.dto.responses;

import java.util.List;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PostsResponseDto {
	private List<ServiceEvaluationPost> serviceEvaluationPosts;
	private PagingResponseDto paging;
}
