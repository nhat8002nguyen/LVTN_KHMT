import { useState } from "react";
import { PostFormDetailState } from "redux/slices/home/posts/postFormSlice";
import { toggleSnackbar } from "redux/slices/statusNotifications/snackbarsSlice";
import { useAppDispatch } from "redux/store/store";

export const validatePostValues = (
	postValues: PostFormDetailState, 
	setPostValues: ReturnType<typeof useState<PostFormDetailState>>[1],
	dispatch: ReturnType<typeof useAppDispatch>
	): Boolean => {
    const { userId, body, hotel } = postValues;
    if (userId < 0) {
      dispatch(
        toggleSnackbar({
          message:
            "Current session is unauthenticated or outdated, please reload page !",
          severity: "error",
        })
      );
      return false;
    }
    if (body.length == 0) {
      dispatch(
        toggleSnackbar({
          message: "Body should not empty, please fill it !",
          severity: "error",
        })
      );
      return false;
    }
    if (Number.isNaN(hotel)) {
      dispatch(
        toggleSnackbar({
          message: "Hotel not found, please enter exiting hotel",
          severity: "error",
        })
      );

      setPostValues((prev) => ({ ...prev, hotel: null }));
    }
    return true;
  };