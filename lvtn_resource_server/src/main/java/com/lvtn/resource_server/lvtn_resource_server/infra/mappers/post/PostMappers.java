package com.lvtn.resource_server.lvtn_resource_server.infra.mappers.post;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
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
public abstract class PostMappers {
	public static PostMappers INSTANCE = Mappers.getMapper(PostMappers.class);

	public abstract ServiceEvaluationPost serviceEvaluationPostEntityToPojo(
			ServiceEvaluationPostEntity serviceEvaluation);

	@AfterMapping
	protected void setAssociatedPostForImages(ServiceEvaluationPost serviceEvaluationPost,
			@MappingTarget ServiceEvaluationPostEntity serviceEvaluationPostEntity) {
		List<PostImageEntity> imageEntities = serviceEvaluationPostEntity.getPostImages();
		imageEntities.forEach(entity -> entity.setServiceEvaluationPost(serviceEvaluationPostEntity));
	}

	public abstract ServiceEvaluationPostEntity serviceEvaluationPostToEntity(ServiceEvaluationPost post);

	public abstract PostImage postImageEntityPostImage(PostImageEntity postImageEntity);

	@Mapping(target = "serviceEvaluationPost", ignore = true)
	public abstract PostImageEntity postImageToPostImageEntity(PostImage postImage);

	@Mapping(target = "serviceEvaluationPosts", ignore = true)
	public abstract HotelEntity hotelToHotelEntity(Hotel hotel);

	public abstract Hotel hotelEntityToHotel(HotelEntity entity);

	@Mapping(target = "serviceEvaluationPosts", ignore = true)
	public abstract UserEntity userToUserEntity(User user);

	public abstract User userEntityToUser(UserEntity entity);
}
