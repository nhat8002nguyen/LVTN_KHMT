package com.lvtn.resource_server.lvtn_resource_server.apis.v1.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvtn.resource_server.lvtn_resource_server.apis.v1.constants.ResponseCode;
import com.lvtn.resource_server.lvtn_resource_server.apis.v1.constants.ResponseMessage;
import com.lvtn.resource_server.lvtn_resource_server.apis.v1.dto.responses.BaseResponseDto;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.exceptions.PostNotFoundException;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.PostDelectionService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.TrendingPostService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.UserPostService;
import com.lvtn.resource_server.lvtn_resource_server.infra.configs.BeanConfigs.Role;

@RestController
@RequestMapping(path = "/api/v1/", produces = "application/json")
public class UserPostController {

	private UserPostService userPostService;
	private TrendingPostService trendingPostService;
	private PostDelectionService postDelectionService;

	@Autowired
	public UserPostController(UserPostService userPostService,
			Map<Role, TrendingPostService> trendingPostService,
			Map<Role, PostDelectionService> postDelectionServices) {
		this.userPostService = userPostService;
		this.trendingPostService = trendingPostService.get(Role.user);
		this.postDelectionService = postDelectionServices.get(Role.user);
	}

	@GetMapping(path = "/posts/recent")
	public ResponseEntity<?> recentPublicPosts() {
		List<ServiceEvaluationPost> posts = userPostService.getNewFeeds(3);

		if (posts == null) {
			BaseResponseDto<?> response = BaseResponseDto.ofFail(
					ResponseCode.POST_RECENT_NOTFOUND, ResponseMessage.POST_RECENT_NOTFOUND);
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(
				BaseResponseDto.ofSucceed(posts, ResponseCode.POST_RECENT_SUCCESS, ResponseMessage.POST_RECENT_SUCCESS));
	}

	// @GetMapping(path = "/posts/recent")
	// public ResponseEntity<?> recentPosts(
	// @AuthenticationPrincipal OidcUser oidcUser) {
	// OidcIdToken idToken = oidcUser.getIdToken();
	// long userId = Integer.parseInt(idToken.getSubject());

	// List<ServiceEvaluationPost> posts = userPostService.getNewFeeds(userId);

	// if (posts.isEmpty() || posts == null) {
	// BaseResponseDto<?> response = BaseResponseDto.ofFail(
	// "user/posts/notfound", "posts not found");
	// return ResponseEntity.badRequest().body(response);
	// }

	// return ResponseEntity.ok(BaseResponseDto.ofSucceed(posts));
	// }

	@GetMapping(path = "/posts/trending/user")
	public ResponseEntity<?> trendingPosts() {
		List<ServiceEvaluationPost> posts = trendingPostService.getTop100TrendingPosts();

		if (posts.isEmpty() || posts == null) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_TREDING_NOTFOUND, ResponseMessage.POST_TREDING_NOTFOUND));
		}
		return ResponseEntity.ok(BaseResponseDto.ofSucceed(posts));
	}

	@PostMapping(path = "/posts")
	public ResponseEntity<?> addPost(@RequestBody ServiceEvaluationPost post) {
		try {

			ServiceEvaluationPost createdPost = userPostService.createPost(post);

			if (createdPost == null) {
				return ResponseEntity.badRequest()
						.body(BaseResponseDto.ofFail(ResponseCode.POST_CREATION_FAIL, ResponseMessage.POST_CREATION_FAIL));
			}

			return ResponseEntity.ok(BaseResponseDto.ofSucceed(createdPost, ResponseCode.POST_CREATION_SUCCESS,
					ResponseMessage.POST_CREATION_SUCCESS));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(BaseResponseDto.ofFail(e.getMessage()));
		}
	}

	@DeleteMapping(path = "/posts/{id}")
	public ResponseEntity<?> deletePost(@PathVariable long id) {
		try {
			postDelectionService.deletePost(id);
			return ResponseEntity.noContent().build();
		} catch (PostNotFoundException e) {
			return ResponseEntity.badRequest().body(BaseResponseDto.ofFail(ResponseCode.POST_DELETION_FAIL, e.getMessage()));
		}
	}

	@PutMapping(path = "/posts/{id}")
	public ResponseEntity<?> updatePost(@PathVariable long id, @RequestBody ServiceEvaluationPost post) {
		try {
			ServiceEvaluationPost updatedPost = userPostService.updatePost(id, post);
			return ResponseEntity.ok(updatedPost);
		} catch (PostNotFoundException e) {
			return ResponseEntity.badRequest().body(BaseResponseDto.ofFail(ResponseCode.POST_DELETION_FAIL, e.getMessage()));
		}
	}
}
