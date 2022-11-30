import { useModal } from "@nextui-org/react";
import { useState } from "react";
import { PostFormDetailState } from "redux/slices/home/posts/postFormSlice";

type UseModelType = ReturnType<typeof useModal>;
type UseStateType = ReturnType<typeof useState<PostFormDetailState>>

export interface PostModalProps {
	setVisible: UseModelType['setVisible'];
	bindings: UseModelType['bindings'];
	postInfo: UseStateType[0];
	setPostInfo: UseStateType[1];
	onCloseClick: () => void;
	onPostClick: () => void;
}

export interface RatingAreaProps {
	postInfo: UseStateType[0];
	setPostInfo: UseStateType[1];
}