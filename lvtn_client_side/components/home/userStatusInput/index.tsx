import {
  AccountCircleRounded,
  AddCircleRounded,
  AddPhotoAlternate,
} from "@material-ui/icons";
import { Rating, Typography } from "@mui/material";
import {
  Avatar,
  Button,
  FormElement,
  Input,
  Loading,
  Modal,
  Text,
  Textarea,
  useModal,
} from "@nextui-org/react";
import { ChangeEvent, ReactElement, useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";
import { AuthState } from "redux/slices/auth/authSlice";
import {
  PostFormDetailState,
  PostFormState,
  postNewEvaluationPost,
} from "redux/slices/home/posts/postFormSlice";
import { toggleSnackbar } from "redux/slices/statusNotifications/snackbarsSlice";
import { RootState, useAppDispatch } from "redux/store/store";
import {
  FileWithURL,
  PhotosAddingProps,
  PostModalProps,
  RatingAreaProps,
} from "./interface";
import styles from "./styles.module.css";

export default function UserStatusInput({ refreshNewsFeed }) {
  const dispatch = useAppDispatch();
  const { setVisible, bindings } = useModal();
  const [postValues, setPostValues] = useState<PostFormDetailState>();
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
    if (bindings.open) {
      setPostValues((prev) => ({
        ...prev,
        title: null,
        body: "",
        hotel: null,
        locationRating: 2.5,
        serviceRating: 2.5,
        cleanlinessRating: 2.5,
        valueRating: 2.5,
        images: [],
      }));
    }
  }, [bindings.open]);

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

  const onCloseClick = () => {
    setVisible(false);
  };

  return (
    <form className={styles.userStatusInput}>
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
    setPostInfo((prev) => ({ ...prev, title: e.target.value?.trim() }));
  };

  const handleBodyChange = (e: ChangeEvent<FormElement>) => {
    setPostInfo((prev) => ({ ...prev, body: e.target.value?.trim() }));
  };

  const handleHotelChange = (e: ChangeEvent<FormElement>) => {
    setPostInfo((prev) => ({
      ...prev,
      hotel: parseInt(e.target.value?.trim()),
    }));
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
      <Modal.Body className={styles.modalBody}>
        <Input
          className={styles.titleInput}
          clearable
          bordered
          label="Title"
          onChange={handleTitleChange}
          color="primary"
        />
        <Textarea
          color="primary"
          label="Description"
          bordered
          onChange={handleBodyChange}
          rows={7}
        />
        <Input
          clearable
          label="Hotel"
          bordered
          onChange={handleHotelChange}
          color="primary"
        />
        <RatingArea postInfo={postInfo} setPostInfo={setPostInfo}></RatingArea>
      </Modal.Body>
      <Modal.Footer justify="space-between">
        <PhotosAdding postInfo={postInfo} setPostInfo={setPostInfo} />
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
          <Typography component="legend">Value</Typography>
          <Rating
            name="half-rating"
            defaultValue={2.5}
            precision={1}
            value={postInfo.valueRating}
            onChange={(event, newValue) => {
              setPostInfo((prev) => ({ ...prev, valueRating: newValue }));
            }}
          />
        </div>
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
    </div>
  );
};

const PhotosAdding = ({
  postInfo,
  setPostInfo,
}: PhotosAddingProps): ReactElement => {
  const fileInputRef = useRef(null);
  const [files, setFiles] = useState<Array<FileWithURL>>([]);

  const openFileUploader = () => {
    if (files.length < 3) {
      fileInputRef.current?.click();
    }
  };

  const handleFileUpload = (e: ChangeEvent) => {
    const fileList = (e.target as any).files as FileList;
    for (let i = 0; i < fileList.length; i++) {
      setFiles((prev) => [
        ...prev,
        { file: fileList[i], url: URL.createObjectURL(fileList[i]) },
      ]);
      setPostInfo((prev: PostFormDetailState) => ({
        ...prev,
        images: [...prev.images, fileList[i]],
      }));
    }
  };

  const removeAllImages = () => {
    setFiles([]);
    setPostInfo((prev) => ({ ...prev, images: [] }));
  };

  return (
    <div className={styles.photoAddingArea}>
      <div onClick={openFileUploader}>
        <AddPhotoAlternate fontSize="large" color="primary" />
      </div>
      <Input
        multiple
        hidden
        ref={fileInputRef}
        type={"file"}
        onChange={handleFileUpload}
      ></Input>
      {files.length > 0 ? (
        <Button auto size="sm" flat color="error" onClick={removeAllImages}>
          Clear
        </Button>
      ) : null}
      <div className={styles.addedFileList}>
        {files.map((file: FileWithURL, index) => (
          <Avatar key={index} pointer squared src={file.url} />
        ))}
      </div>
    </div>
  );
};
