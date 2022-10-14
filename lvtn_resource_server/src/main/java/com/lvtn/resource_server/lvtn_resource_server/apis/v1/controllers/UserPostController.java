package com.lvtn.resource_server.lvtn_resource_server.apis.v1.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lvtn.resource_server.lvtn_resource_server.apis.v1.constants.ResponseCode;
import com.lvtn.resource_server.lvtn_resource_server.apis.v1.constants.ResponseMessage;
import com.lvtn.resource_server.lvtn_resource_server.apis.v1.dto.responses.BaseResponseDto;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.exceptions.PostNotFoundException;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.Hotel;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.PostImage;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost.PostVisibility;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.PostDelectionService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.TrendingPostService;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.services.UserPostService;
import com.lvtn.resource_server.lvtn_resource_server.infra.configs.BeanConfigs.Role;

@RestController
@RequestMapping(path = "/api/v1/", produces = "application/json")
@CrossOrigin
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

	@PatchMapping(path = "/posts/{id}/title")
	public ResponseEntity<?> updatePostTitle(@PathVariable long id, @RequestParam String title) {
		try {
			ServiceEvaluationPost updatedPost = userPostService.updatePostTitle(id, title);
			return ResponseEntity.ok(updatedPost);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_UPDATE_FAIL, ResponseMessage.POST_UPDATE_FAIL));
		}
	}

	@PatchMapping(path = "/posts/{id}/body")
	public ResponseEntity<?> updatePostBody(@PathVariable long id, @RequestParam String body) {
		try {
			ServiceEvaluationPost updatedPost = userPostService.updatePostBody(id, body);
			return ResponseEntity.ok(updatedPost);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_UPDATE_FAIL, ResponseMessage.POST_UPDATE_FAIL));
		}
	}

	@PatchMapping(path = "/posts/{id}/post_images")
	public ResponseEntity<?> updatePostImages(@PathVariable long id, @RequestBody List<PostImage> postImages) {
		try {
			Set<PostImage> imageSet = new HashSet<>();
			postImages.forEach(image -> imageSet.add(image));
			ServiceEvaluationPost updatedPost = userPostService.updatePostImages(id, imageSet);
			return ResponseEntity.ok(updatedPost);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_UPDATE_FAIL, ResponseMessage.POST_UPDATE_FAIL));
		}
	}

	@PatchMapping(path = "/posts/{id}/hotel")
	public ResponseEntity<?> updatePostHotel(@PathVariable long id, @RequestBody Hotel hotel) {
		try {
			ServiceEvaluationPost updatedPost = userPostService.updatePostHotel(id, hotel);
			return ResponseEntity.ok(updatedPost);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_UPDATE_FAIL, ResponseMessage.POST_UPDATE_FAIL));
		}
	}

	@PatchMapping(path = "/posts/{id}/location_rating")
	public ResponseEntity<?> updateLocationRating(@PathVariable long id, @RequestParam double locationRating) {
		try {
			ServiceEvaluationPost updatedPost = userPostService.updatePostLocationRating(id, locationRating);
			return ResponseEntity.ok(updatedPost);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_UPDATE_FAIL, ResponseMessage.POST_UPDATE_FAIL));
		}
	}

	@PatchMapping(path = "/posts/{id}/cleanliness_rating")
	public ResponseEntity<?> updateCleanlinessRating(@PathVariable long id, @RequestParam double cleanlinessRating) {
		try {
			ServiceEvaluationPost updatedPost = userPostService.updatePostCleanlinessRating(id, cleanlinessRating);
			return ResponseEntity.ok(updatedPost);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_UPDATE_FAIL, ResponseMessage.POST_UPDATE_FAIL));
		}
	}

	@PatchMapping(path = "/posts/{id}/service_rating")
	public ResponseEntity<?> updateServiceRating(@PathVariable long id, @RequestParam double serviceRating) {
		try {
			ServiceEvaluationPost updatedPost = userPostService.updatePostServiceRating(id, serviceRating);
			return ResponseEntity.ok(updatedPost);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_UPDATE_FAIL, ResponseMessage.POST_UPDATE_FAIL));
		}
	}

	@PatchMapping(path = "/posts/{id}/liked_count")
	public ResponseEntity<?> updatePostLikedCount(@PathVariable long id, @RequestParam int likedCount) {
		try {
			ServiceEvaluationPost updatedPost = userPostService.updatePostLikedCount(id, likedCount);
			return ResponseEntity.ok(updatedPost);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_UPDATE_FAIL, ResponseMessage.POST_UPDATE_FAIL));
		}
	}

	@PatchMapping(path = "/posts/{id}/disliked_count")
	public ResponseEntity<?> updatePostDislikedCount(@PathVariable long id, @RequestParam int dislikedCount) {
		try {
			ServiceEvaluationPost updatedPost = userPostService.updatePostDislikedCount(id, dislikedCount);
			return ResponseEntity.ok(updatedPost);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_UPDATE_FAIL, ResponseMessage.POST_UPDATE_FAIL));
		}
	}

	@PatchMapping(path = "/posts/{id}/shared_count")
	public ResponseEntity<?> updatePostSharedCount(@PathVariable long id, @RequestParam int sharedCount) {
		try {
			ServiceEvaluationPost updatedPost = userPostService.updatePostSharedCount(id, sharedCount);
			return ResponseEntity.ok(updatedPost);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_UPDATE_FAIL, ResponseMessage.POST_UPDATE_FAIL));
		}
	}

	@PatchMapping(path = "/posts/{id}/visibility")
	public ResponseEntity<?> updatePostVisibility(@PathVariable long id, @RequestParam PostVisibility visibility) {
		try {
			ServiceEvaluationPost updatedPost = userPostService.updatePostVisibility(id, visibility);
			return ResponseEntity.ok(updatedPost);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(BaseResponseDto.ofFail(ResponseCode.POST_UPDATE_FAIL, ResponseMessage.POST_UPDATE_FAIL));
		}
	}
}
