package com.lvtn.resource_server.lvtn_resource_server.domains.posts.exceptions;

public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException() {
		super(PostError.POST_NOT_FOUND_ERROR);
	}
}
