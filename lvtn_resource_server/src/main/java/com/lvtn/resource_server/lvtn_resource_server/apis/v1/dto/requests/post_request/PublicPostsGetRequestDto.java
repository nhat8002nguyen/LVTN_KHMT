package com.lvtn.resource_server.lvtn_resource_server.apis.v1.dto.requests.post_request;

import lombok.Data;

@Data
public class PublicPostsGetRequestDto {
	private String username;
	private int page;
	private int size;
}
