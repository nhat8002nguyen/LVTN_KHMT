import { randomUUID } from "crypto";
import { cloudinaryAxios, harusaAxios } from "utils/axios/axios";
import { PostFormDetailState } from "./postFormSlice";
import { EvaluationPostDto, PostImageDto } from "./postListAPI";

export const saveEvaluationPost = async (
  post: PostFormDetailState
): Promise<PostSaveResponseDto> => {
  const imagesSavingResponses = await saveImagesToCloudinary(post);

  const purePostRes = await saveEvaluationPostWithoutImages(post);

  if (purePostRes.status == 200) {
    if (post.images.length == 0) {
      return purePostRes.data;
    } else if (
      post.images.length > 0 &&
      imagesSavingResponses[0].status == 200
    ) {
      const postId = (purePostRes.data as PostSaveResponseDto)
        .insert_evaluation_post.returning[0].id;
      if (postId != null) {
        const imagesSavingDtos = imagesSavingResponses.map(
          (res) => res.data as ImagesSavingCloudinaryDto
        );
        const imageRefsResponses = await addImageReferencesToDB(
          postId,
          imagesSavingDtos
        );
        if (imageRefsResponses[0]?.status == 200) {
          return combinePostWithImages(
            purePostRes.data,
            imageRefsResponses.map((res) => res.data as PostImageInsertionDto)
          );
        }
      }
    }
  }
};

const saveEvaluationPostWithoutImages = async (post: PostFormDetailState) => {
  try {
    const purePostRes = await harusaAxios.post("/posts", null, {
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
    return purePostRes;
  } catch (err) {
    throw Error("Can not save post to DB");
  }
};

const saveImagesToCloudinary = async (post: PostFormDetailState) => {
  try {
    const imagesSavingPromises = post.images.map((image) => {
      let formData = new FormData();
      formData.append("file", image);
      formData.append(
        "upload_preset",
        process.env.NEXT_PUBLIC_CLOUDINARY_UPLOAD_PRESET
      );
      formData.append("public_id", image.name + "-" + randomUUID);
      formData.append("api_key", process.env.NEXT_PUBLIC_CLOUDINARY_API_KEY);
      return cloudinaryAxios.post("/:resource_type/upload", formData, {
        params: { resource_type: "image" },
      });
    });

    const imagesSavingResponses = await Promise.all(imagesSavingPromises);

    return imagesSavingResponses;
  } catch (err) {
    console.error(err);
    throw Error("Can not upload images, please try again !");
  }
};

const addImageReferencesToDB = async (
  postId: number,
  imageSavingDtos: Array<ImagesSavingCloudinaryDto>
) => {
  try {
    const imageReferencesPromises = imageSavingDtos.map((dto) => {
      return harusaAxios.post("/posts/images", null, {
        params: {
          post_id: postId,
          url: dto.url,
        },
      });
    });
    return await Promise.all(imageReferencesPromises);
  } catch (err) {
    console.error(err);
    throw Error("Can add image to DB please try again !");
  }
};

const combinePostWithImages = (
  postDto: PostSaveResponseDto,
  imageRefs: PostImageInsertionDto[]
): PostSaveResponseDto => {
  const postImages = imageRefs.map(
    (image) =>
      ({
        id: image.insert_post_image.returning[0].id,
        url: image.insert_post_image.returning[0].url,
      } as PostImageDto)
  );
  postDto.insert_evaluation_post.returning[0].post_images = postImages;
  return postDto;
};

export interface PostSaveResponseDto {
  insert_evaluation_post: InsertPostDto;
}

export interface InsertPostDto {
  returning: Array<EvaluationPostDto>;
}

export interface ImagesSavingCloudinaryDto {
  asset_id: string;
  public_id: string;
  version: number;
  version_id: string;
  signature: string;
  width: number;
  height: number;
  format: string;
  resource_type: string;
  created_at: string;
  tags: Array<string>;
  bytes: number;
  type: string;
  etag: string;
  placeholder: false;
  url: string;
  secure_url: string;
  folder: string;
  access_mode: string;
  original_filename: string;
}

export interface PostImageInsertionDto {
  insert_post_image: InsertPostImageDto;
}

export interface InsertPostImageDto {
  returning: PostImageReturningDto[];
}

export interface PostImageReturningDto {
  id: number;
  post: number;
  url: string;
}
