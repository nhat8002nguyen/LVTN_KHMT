import { EvaluationPostDto, PostListResponseDto } from "./postListAPI";
import { ImageState, PostState } from "./postListSlice";

export const updatePostsFromResponse = (
  response: PostListResponseDto
): Array<PostState> => {
  const totalResponsePosts: Array<EvaluationPostDto> = [];
  response.user[0].followers.forEach((person) =>
    totalResponsePosts.push(...person.following_user.evaluation_posts)
  );
  totalResponsePosts.push(...response.user[0].evaluation_posts);

  const posts = totalResponsePosts.map(
    (dto) =>
      <PostState>{
        id: dto.id,
        postOwner: {
          username: dto.post_owner.user_name,
          image: dto.post_owner.image,
          email: dto.post_owner.email,
        },
        title: dto.title,
        body: dto.body,
        images: dto.post_images.map(
          (imageDto) =>
            ({
              id: imageDto.id,
              url: imageDto.url,
            } as ImageState)
        ),
        hotel:
          dto.post_hotel == null
            ? null
            : {
                id: dto.post_hotel.id,
                name: dto.post_hotel.name,
                location: dto.post_hotel.location,
                about: dto.post_hotel.about,
                rating: dto.post_hotel.rating,
              },
        locationRating: dto.location_rating,
        cleanlinessRating: dto.cleanliness_rating,
        serviceRating: dto.service_rating,
        valueRating: dto.value_rating,
        likedCount: dto.post_likes_aggregate.aggregate.count,
        sharedCount: dto.post_shares_aggregate.aggregate.count,
        commentCount: dto.post_comments_aggregate.aggregate.count,
        createdAt: new Date(dto.created_at),
        updatedAt: new Date(dto.updated_at),
      }
  );

  return posts;
};
