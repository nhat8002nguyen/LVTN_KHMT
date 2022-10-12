package com.lvtn.resource_server.lvtn_resource_server.apis.v1.dto.responses;

import java.util.List;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RecentPostsResponseDto extends BaseResponseDto<RecentPostsResponseDto> {
	private List<ServiceEvaluationPost> serviceEvaluationPosts;
}
