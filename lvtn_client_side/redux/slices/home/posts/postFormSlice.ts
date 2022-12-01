import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { RootState } from "../../../store/store";
import * as postFormAPI from "./postFormAPI";

export interface PostFormState {
  requestStatus: "idle" | "pending" | "succeeded" | "failed";
  message: string | null;
}

export interface PostFormDetailState {
  userId?: number;
  title: string | null;
  body: string;
  hotel: number | null;
  locationRating: number;
  serviceRating: number;
  cleanlinessRating: number;
  valueRating: number;
  images: Array<File>;
}

const initialState: PostFormState = {
  requestStatus: "idle",
  message: null,
};

export const postFormSlice = createSlice({
  name: "postForm",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(postNewEvaluationPost.pending, (state, action) => {
      state.requestStatus = "pending";
    });
    builder.addCase(postNewEvaluationPost.fulfilled, (state, action) => {
      state.requestStatus = "succeeded";
    });
    builder.addCase(postNewEvaluationPost.rejected, (state, action) => {
      state.requestStatus = "failed";
    });
  },
});

export const postNewEvaluationPost = createAsyncThunk(
  "posts/addNewPost",
  async (post: PostFormDetailState, thunkAPI) => {
    return await postFormAPI.saveEvaluationPost(post);
  }
);

export const selectPostListState = (state: RootState) => state.postForm;

export default postFormSlice.reducer;
