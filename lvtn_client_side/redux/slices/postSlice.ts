import { createSlice } from "@reduxjs/toolkit";
import { RootState } from "../store/store";

export interface PostState {
  postState: boolean;
}

const initialState: PostState = {
  postState: false,
};

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {

    setPostState(state, action) {
      state.postState = action.payload;
    },

  },
});

export const { setPostState } = authSlice.actions;

export const selectPostState = (state: RootState) => state.auth.postState;

export default authSlice.reducer;