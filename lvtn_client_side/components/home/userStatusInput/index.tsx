import {
  AccountCircleRounded,
  AddCircleRounded,
  AddPhotoAlternate,
} from "@material-ui/icons";
import { Rating, Typography } from "@mui/material";
import {
  Button,
  FormElement,
  Input,
  Loading,
  Modal,
  Text,
  Textarea,
  useModal,
} from "@nextui-org/react";
import Head from "next/head";
import Script from "next/script";
import { ChangeEvent, ReactElement, useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { AuthState } from "redux/slices/auth/authSlice";
import {
  PostFormDetailState,
  PostFormState,
  postNewEvaluationPost,
} from "redux/slices/home/posts/postFormSlice";
import { toggleSnackbar } from "redux/slices/statusNotifications/snackbarsSlice";
import { RootState, useAppDispatch } from "redux/store/store";
import { PostModalProps, RatingAreaProps } from "./interface";
import styles from "./styles.module.css";

export default function UserStatusInput({ refreshNewsFeed }) {
  const dispatch = useAppDispatch();
  const { setVisible, bindings } = useModal();
  const [postValues, setPostValues] = useState<PostFormDetailState>({
    userId: -1,
    title: null,
    body: "",
    hotel: null,
    locationRating: 2.5,
    serviceRating: 2.5,
    cleanlinessRating: 2.5,
    valueRating: 2.5,
    images: [],
  });
  const { requestStatus }: PostFormState = useSelector(
    (state: RootState) => state.postForm
  );
  const { session, sessionStatus }: AuthState = useSelector(
    (state: RootState) => state.auth
  );

  useEffect(() => {
    if (sessionStatus == "authenticated" && session.user?.db_id != null) {
      setPostValues((prev) => ({ ...prev, userId: session.user.db_id }));
    }
  }, [sessionStatus, session]);

  useEffect(() => {
    if (requestStatus == "failed") {
      dispatch(
        toggleSnackbar({
          message: "Failed to create new post, please try again !",
          severity: "error",
        })
      );
    }
    if (requestStatus == "succeeded") {
      setVisible(false);
      refreshNewsFeed();
      dispatch(
        toggleSnackbar({
          message: "Create a new post successfully !",
          severity: "success",
        })
      );
    }
  }, [requestStatus]);

  const onPostClick = () => {
    const isValid = validatePostValues(postValues);

    if (isValid == false) {
      return;
    }

    dispatch(postNewEvaluationPost(postValues));
  };

  const validatePostValues = (postValues: PostFormDetailState): Boolean => {
    const { userId, body, hotel } = postValues;
    if (userId < 0) {
      alert("Current session is outdated or unauthenticated");
      return false;
    }
    if (body.length == 0) {
      alert("Body should not empty, please add it !");
      return false;
    }
    if (Number.isNaN(hotel)) {
      alert("Cannot find hotel.");
      setPostValues((prev) => ({ ...prev, hotel: null }));
    }
    return true;
  };

  const onCloseClick = () => {
    setVisible(false);
  };

  return (
    <form className={styles.userStatusInput}>
      <Head>
        <Script
          src="https://widget.cloudinary.com/v2.0/global/all.js"
          type="text/javascript"
        ></Script>
      </Head>
      <AccountCircleRounded className={styles.avatarIcon} />
      <div className={styles.formHeader}>
        <label className={styles.formLabel} onClick={() => setVisible(true)}>
          Let's give a review
        </label>
      </div>
      <AddCircleRounded
        className={styles.addIcon}
        onClick={() => setVisible(true)}
      />
      <PostModal
        setVisible={setVisible}
        bindings={bindings}
        postInfo={postValues}
        setPostInfo={setPostValues}
        onPostClick={onPostClick}
        onCloseClick={onCloseClick}
      ></PostModal>
    </form>
  );
}

const PostModal = ({
  setVisible,
  bindings,
  postInfo,
  setPostInfo,
  onPostClick,
  onCloseClick,
}: PostModalProps): ReactElement => {
  const { requestStatus }: PostFormState = useSelector(
    (state: RootState) => state.postForm
  );

  const handleTitleChange = (e: ChangeEvent<FormElement>) => {
    setPostInfo((prev) => ({ ...prev, title: e.target.value }));
  };

  const handleBodyChange = (e: ChangeEvent<FormElement>) => {
    setPostInfo((prev) => ({ ...prev, body: e.target.value }));
  };

  const handleHotelChange = (e: ChangeEvent<FormElement>) => {
    setPostInfo((prev) => ({ ...prev, hotel: parseInt(e.target.value) }));
  };

  return (
    <Modal
      scroll
      width="600px"
      aria-labelledby="modal-title"
      aria-describedby="modal-description"
      {...bindings}
    >
      <Modal.Header>
        <Text id="modal-title" size={18}>
          Let's give a review
        </Text>
      </Modal.Header>
      <Modal.Body>
        <Input
          clearable
          underlined
          placeholder="Title"
          onChange={handleTitleChange}
          color="primary"
        />
        <Textarea
          bordered
          color="primary"
          placeholder="Write some description here... *"
          onChange={handleBodyChange}
          rows={7}
        />
        <Input
          clearable
          underlined
          placeholder="Hotel"
          onChange={handleHotelChange}
          color="primary"
        />
        <RatingArea postInfo={postInfo} setPostInfo={setPostInfo}></RatingArea>
      </Modal.Body>
      <Modal.Footer justify="space-between">
        <div className={styles.photoAddingArea}>
          <AddPhotoAlternate fontSize="large" color="primary" />
        </div>
        <div className={styles.footerButtons}>
          <Button auto flat color="error" onClick={onCloseClick}>
            Close
          </Button>
          <Button
            auto
            onClick={onPostClick}
            disabled={requestStatus == "pending"}
          >
            {requestStatus == "pending" ? (
              <Loading type="points" color="white" size="sm" />
            ) : (
              "Post"
            )}
          </Button>
        </div>
      </Modal.Footer>
    </Modal>
  );
};

const RatingArea = ({
  postInfo,
  setPostInfo,
}: RatingAreaProps): ReactElement => {
  return (
    <div className={styles.ratingArea}>
      <div className={styles.ratingAreaRow}>
        <div className={styles.rating}>
          <Typography component="legend">Location</Typography>
          <Rating
            name="half-rating"
            defaultValue={2.5}
            precision={0.5}
            value={postInfo.locationRating}
            onChange={(event, newValue) => {
              setPostInfo((prev) => ({ ...prev, locationRating: newValue }));
            }}
          />
        </div>
        <div className={styles.rating}>
          <Typography component="legend">Service</Typography>
          <Rating
            name="half-rating"
            defaultValue={2.5}
            precision={0.5}
            value={postInfo.serviceRating}
            onChange={(event, newValue) => {
              setPostInfo((prev) => ({ ...prev, serviceRating: newValue }));
            }}
          />
        </div>
      </div>
      <div className={styles.ratingAreaRow}>
        <div className={styles.rating}>
          <Typography component="legend">Clealiness</Typography>
          <Rating
            name="half-rating"
            defaultValue={2.5}
            precision={0.5}
            value={postInfo.cleanlinessRating}
            onChange={(event, newValue) => {
              setPostInfo((prev) => ({ ...prev, cleanlinessRating: newValue }));
            }}
          />
        </div>
        <div className={styles.rating}>
          <Typography component="legend">Value</Typography>
          <Rating
            name="half-rating"
            defaultValue={2.5}
            precision={0.5}
            value={postInfo.valueRating}
            onChange={(event, newValue) => {
              setPostInfo((prev) => ({ ...prev, valueRating: newValue }));
            }}
          />
        </div>
      </div>
    </div>
  );
};
