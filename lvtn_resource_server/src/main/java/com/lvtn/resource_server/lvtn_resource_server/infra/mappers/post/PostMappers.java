package com.lvtn.resource_server.lvtn_resource_server.infra.mappers.post;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.Hotel;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.PostImage;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.User;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.HotelEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.PostImageEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.ServiceEvaluationPostEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.UserEntity;

@Mapper
public interface PostMappers {
	public static PostMappers INSTANCE = Mappers.getMapper(PostMappers.class);

	ServiceEvaluationPost serviceEvaluationPostEntityToPojo(
			ServiceEvaluationPostEntity serviceEvaluation);

	ServiceEvaluationPostEntity serviceEvaluationPostToEntity(ServiceEvaluationPost post);

	PostImage postImageEntityPostImage(PostImageEntity postImageEntity);

	PostImageEntity postImageToPostImageEntity(PostImage postImage);

	HotelEntity hotelToHotelEntity(Hotel hotel);

	Hotel hoteEntityToHotel(HotelEntity entity);

	UserEntity userToUserEntity(User user, List<ServiceEvaluationPost> serviceEvaluationPosts);

	User userEntityToUser(UserEntity entity);

	UserEntity userToUserEntity(User user);
}
