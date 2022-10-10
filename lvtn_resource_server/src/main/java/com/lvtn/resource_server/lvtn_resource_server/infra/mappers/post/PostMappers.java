package com.lvtn.resource_server.lvtn_resource_server.infra.mappers.post;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.Hotel;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.PostImage;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.PropertyAmenity;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.PropertyAmenityEvaluation;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.RoomFeature;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.RoomFeatureEvaluation;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.ServiceEvaluationPost;
import com.lvtn.resource_server.lvtn_resource_server.domains.posts.pojos.User;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.HotelEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.PostImageEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.PropertyAmenityEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.RoomFeatureEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.ServiceEvaluationPostEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.UserEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.property_amenity_evaluation.PropertyAmenityEvaluationEntity;
import com.lvtn.resource_server.lvtn_resource_server.infra.entities.room_feature_evaluation.RoomFeatureEvaluationEntity;

@Mapper
public interface PostMappers {
	public static PostMappers INSTANCE = Mappers.getMapper(PostMappers.class);

	ServiceEvaluationPost serviceEvaluationPostEntityToPojo(
			ServiceEvaluationPostEntity serviceEvaluation,
			List<PropertyAmenityEvaluationEntity> propertyAmenityEvaluations,
			List<RoomFeatureEvaluationEntity> roomFeatureEvaluations);

	ServiceEvaluationPostEntity serviceEvaluationPostToEntity(ServiceEvaluationPost post);

	PostImage postImageEntityPostImage(PostImageEntity postImageEntity);

	PostImageEntity postImageToPostImageEntity(PostImage postImage);

	PropertyAmenityEvaluation propertyAmenityEvaluationEntityToPojo(
			PropertyAmenityEvaluationEntity entity);

	PropertyAmenityEvaluationEntity propertyAmenityEvaluationPojoToEntity(
			ServiceEvaluationPost serviceEvaluation,
			PropertyAmenityEvaluation pojo);

	PropertyAmenityEntity propertyAmenityPojoToEntity(PropertyAmenity pojo);

	RoomFeatureEvaluation roomFeatureEvaluationEntityToPojo(
			RoomFeatureEvaluationEntity propertyAmenityEvaluationEntity);

	RoomFeatureEvaluationEntity roomFeatureEvaluationToEntity(
			RoomFeatureEvaluation pojo,
			ServiceEvaluationPost serviceEvaluation);

	RoomFeatureEntity roomFeaturePojoToEntity(RoomFeature pojo);

	RoomFeature roomFeatureEntityToPojo(RoomFeatureEntity entity);

	HotelEntity hotelToHotelEntity(Hotel hotel);

	Hotel hoteEntityToHotel(HotelEntity entity);

	UserEntity userToUserEntity(User user, List<ServiceEvaluationPost> serviceEvaluationPosts);

	User userEntityToUser(UserEntity entity);

	UserEntity userToUserEntity(User user);
}
