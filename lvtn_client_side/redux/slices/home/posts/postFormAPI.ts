import { harusaAxios } from "utils/axios/axios";
import { PostFormDetailState } from "./postFormSlice";
import { EvaluationPostDto } from "./postListAPI";

export const saveEvaluationPost = async (
  post: PostFormDetailState
): Promise<PostSaveResponseDto> => {
  try {
    const res = await harusaAxios.post("/posts", null, {
      params: {
        user_id: post.userId,
        title: post.title,
        body: post.body,
        hotel: post.hotel,
        location_rating: post.locationRating,
        cleanliness_rating: post.cleanlinessRating,
        service_rating: post.serviceRating,
        value_rating: post.valueRating,
      },
    });

    //TODO: add request to store image to cloud and get the url
    // to make a request to save image to database

    if (res.status == 200) {
      return res.data;
    }
  } catch (err) {
    console.error("Error: ", err);
  }
};

export interface PostSaveResponseDto {
  insert_evaluation_post: InsertPostDto;
}

export interface InsertPostDto {
  returning: Array<EvaluationPostDto>;
}
