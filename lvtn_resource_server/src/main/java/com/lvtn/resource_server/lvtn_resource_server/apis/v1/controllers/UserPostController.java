package com.lvtn.resource_server.lvtn_resource_server.apis.v1.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvtn.resource_server.lvtn_resource_server.apis.v1.dto.responses.BaseResponse;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.TrendingPostService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.UserPostService;
import com.lvtn.resource_server.lvtn_resource_server.infra.configs.BeanConfigs.Role;

@RestController
@RequestMapping(path = "/api/v1/", produces = "application/json")
public class UserPostController {

	private UserPostService userPostService;
	private TrendingPostService trendingPostService;

	@Autowired
	public UserPostController(UserPostService userPostService,
			Map<Role, TrendingPostService> trendingPostService) {
		this.userPostService = userPostService;
		this.trendingPostService = trendingPostService.get(Role.user);
	}

	@GetMapping(path = "/posts/recent")
	public ResponseEntity<?> recentPublicPosts() {
		List<ServiceEvaluationPost> posts = userPostService.getNewFeeds(3);

		if (posts == null) {
			BaseResponse<?> response = BaseResponse.ofFail(
					"user/posts/notfound", "posts not found");
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(
				BaseResponse.ofSucceed(posts, "user/posts/recent", "Successfully get recent posts"));
	}

	// @GetMapping(path = "/posts/recent")
	// public ResponseEntity<?> recentPosts(
	// @AuthenticationPrincipal OidcUser oidcUser) {
	// OidcIdToken idToken = oidcUser.getIdToken();
	// long userId = Integer.parseInt(idToken.getSubject());

	// List<ServiceEvaluationPost> posts = userPostService.getNewFeeds(userId);

	// if (posts.isEmpty() || posts == null) {
	// BaseResponse<?> response = BaseResponse.ofFail(
	// "user/posts/notfound", "posts not found");
	// return ResponseEntity.badRequest().body(response);
	// }

	// return ResponseEntity.ok(BaseResponse.ofSucceed(posts));
	// }

	@GetMapping(path = "/posts/trending/user")
	public ResponseEntity<?> trendingPosts() {
		List<ServiceEvaluationPost> posts = trendingPostService.getTop100TrendingPosts();

		if (posts.isEmpty() || posts == null) {
			return ResponseEntity.badRequest()
					.body(BaseResponse.ofFail("user/posts/trending/notfound", "Trending posts of user not found"));
		}
		return ResponseEntity.ok(BaseResponse.ofSucceed(posts));
	}
}
