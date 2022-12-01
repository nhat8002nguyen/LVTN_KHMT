import { imageUrlAlt } from "@/constants/homeConstants";
import { appColors } from "@/shared/theme";
import { Typography } from "@material-ui/core";
import {
  CheckCircle,
  ModeCommentOutlined,
  MoreVertRounded,
  ScreenShareOutlined,
  Send,
  ShareOutlined,
  ThumbUp,
  ThumbUpOutlined,
} from "@material-ui/icons";
import { Rating } from "@mui/material";
import { Avatar, Card, Input, Loading, Text } from "@nextui-org/react";
import Image from "next/image";
import React, { ReactElement, useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { PostState } from "redux/slices/home/posts/postListSlice";
import { RootState } from "redux/store/store";
import styles from "./styles.module.css";

export default function EvaluationPost(props) {
  const { id } = props;
  const postListState = useSelector((state: RootState) => state.postList);
  const [postProps, setPostProps] = useState<PostState>();

  useEffect(() => {
    const post = (postListState.posts as Array<PostState>).find(
      (value) => value.id == id
    );
    setPostProps(post);
  }, []);

  const getDayMonth = () => {
    const monthNames = [
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Set",
      "Oct",
      "Nov",
      "Dec",
    ];

    let date = new Date(postProps.createdAt);
    return date.getDate() + " " + monthNames[date.getMonth()];
  };

  return (
    <Card
      css={{ minHeight: "30rem", maxWidth: "40rem", backgroundColor: "white" }}
    >
      {!postProps ? (
        <Loading type="points" color="currentColor" size="sm" />
      ) : (
        <div className={styles.postContainer}>
          <Avatar src={postProps.postOwner.image} rounded />
          <div className={styles.postMain}>
            <div className={styles.header}>
              <div className={styles.headerLeft}>
                <Text css={{ fontWeight: "bold" }}>
                  {postProps.postOwner.username}
                </Text>
                {true ? <CheckCircle color="primary" fontSize="small" /> : null}
                <Text css={{ fontSize: "small" }}>
                  {postProps.postOwner.email}
                </Text>
              </div>
              <div className={styles.headerRight}>
                <Text css={{ fontSize: "small" }}>{getDayMonth()}</Text>
                <MoreVertRounded />
              </div>
            </div>
            <div className={styles.content}>
              <Text className={styles.title}>{postProps.title}</Text>
              <div className={styles.descriptions}>
                <Text>{postProps.body}</Text>
              </div>
              <div className={styles.ratingAndHotelDetail}>
                <PostRatingArea
                  locationRating={postProps.locationRating}
                  serviceRating={postProps.serviceRating}
                  cleanlinessRating={postProps.cleanlinessRating}
                  valueRating={postProps.valueRating}
                />
                {!postProps.hotel ? null : (
                  <div className={styles.hotelDetail}>
                    <Text
                      className={styles.hotelText}
                      color={appColors.primary}
                    >
                      {"Hotel: " + postProps.hotel.name}
                    </Text>
                    <Text
                      className={styles.hotelText}
                      color={appColors.primary}
                    >
                      {"Location: " + postProps.hotel.location}
                    </Text>
                  </div>
                )}
              </div>
              <PostImages
                images={{
                  first: postProps.images[0]?.url,
                  second: postProps.images[1]?.url,
                  third: postProps.images[2]?.url,
                }}
              />
              <InteractionMetrics
                numLikeds={postProps.likedCount}
                numTexts={postProps.sharedCount}
                numShareds={postProps.commentCount}
              />
              <CommentArea
                avatar={postProps.postOwner.image}
                threadChats={[]}
              />
            </div>
          </div>
        </div>
      )}
    </Card>
  );
}

const PostImages = ({ images }) => {
  if (images.first == null && images.second == null && images.third == null) {
    return;
  }
  if (images.first != null && images.second == null && images.third == null) {
    return (
      <Image
        style={{ cursor: "pointer" }}
        src={images.first}
        alt={imageUrlAlt.postAlt}
        width={450}
        height={300}
        objectFit="cover"
      />
    );
  }

  if (images.first != null && images.second != null && images.third == null) {
    return (
      <div>
        <Image
          style={{ cursor: "pointer" }}
          src={images.first}
          alt={imageUrlAlt.postAlt}
          width={225}
          height={225}
          objectFit="cover"
        />
        <Image
          style={{ cursor: "pointer" }}
          src={images.second}
          alt={imageUrlAlt.postAlt}
          width={225}
          height={225}
          objectFit="cover"
        />
      </div>
    );
  }
  if (images.first != null && images.second != null && images.third != null) {
    return (
      <div className={styles.images}>
        <div className={styles.smallImages}>
          <Image
            style={{ cursor: "pointer" }}
            src={images.first}
            alt={imageUrlAlt.postAlt}
            width={150}
            height={150}
            objectFit="cover"
          />
          <Image
            style={{ cursor: "pointer" }}
            src={images.second}
            alt={imageUrlAlt.postAlt}
            width={150}
            height={150}
            objectFit="cover"
          />
        </div>
        <div className={styles.bigImage}>
          <Image
            style={{ cursor: "pointer" }}
            src={images.third}
            alt={imageUrlAlt.postAlt}
            width={300}
            height={300}
            objectFit="cover"
          />
        </div>
      </div>
    );
  }
};

const PostRatingArea = ({
  locationRating,
  serviceRating,
  cleanlinessRating,
  valueRating,
}: PostRatingArea): ReactElement => {
  return (
    <div className={styles.ratingArea}>
      <div className={styles.ratingAreaRow}>
        <div className={styles.rating}>
          <Typography
            style={{ fontSize: "12px" }}
            className={styles.ratingText}
            component="legend"
          >
            Value ---------
          </Typography>
          <Rating
            size="small"
            name="read-only"
            precision={1}
            value={valueRating}
            readOnly
          />
        </div>
        <div className={styles.rating}>
          <Typography style={{ fontSize: "12px" }} component="legend">
            Location -----
          </Typography>
          <Rating
            size="small"
            name="read-only"
            precision={0.5}
            value={locationRating}
            readOnly
          />
        </div>
        <div className={styles.rating}>
          <Typography style={{ fontSize: "12px" }} component="legend">
            Service ------
          </Typography>
          <Rating
            size="small"
            name="read-only"
            precision={0.5}
            value={serviceRating}
            readOnly
          />
        </div>
      </div>
      <div className={styles.ratingAreaRow}>
        <div className={styles.rating}>
          <Typography
            style={{ fontSize: "12px" }}
            className={styles.ratingText}
            component="legend"
          >
            Clealiness --
          </Typography>
          <Rating
            size="small"
            name="read-only"
            precision={0.5}
            value={cleanlinessRating}
            readOnly
          />
        </div>
      </div>
    </div>
  );
};

const InteractionMetrics = ({ numLikeds, numTexts, numShareds }) => {
  const [liked, setLiked] = React.useState(false);

  const toggleLike = () => setLiked(() => !liked);

  const showNum = (num) => {
    const numStr = String(num);
    if (num < 1000) return numStr;

    if (num < 1000000) {
      if (num % 1000 == 0) {
        return numStr.substring(0, numStr.length - 3) + "k";
      } else {
        return (
          numStr.substring(0, numStr.length - 3) +
          "." +
          numStr.charAt(numStr.length - 3) +
          "k"
        );
      }
    }

    if (num % 1000000 == 0) {
      return numStr.substring(0, numStr.length - 6) + "k";
    } else {
      return (
        numStr.substring(0, numStr.length - 6) +
        "." +
        numStr.charAt(numStr.length - 6) +
        "m"
      );
    }
  };

  return (
    <div className={styles.interactionMetrics}>
      <div className={styles.metric}>
        <LikeIcon onClick={toggleLike} liked={liked} />
        <Text css={{ fontSize: "small" }}>{showNum(numLikeds)}</Text>
      </div>
      <div className={styles.metric}>
        <ModeCommentOutlined />
        <Text css={{ fontSize: "small" }}>{showNum(numTexts)}</Text>
      </div>
      <div className={styles.metric}>
        <ScreenShareOutlined />
        <Text css={{ fontSize: "small" }}>{showNum(numShareds)}</Text>
      </div>
      <div className={styles.metric}>
        <ShareOutlined />
      </div>
    </div>
  );
};

const LikeIcon = ({ onClick, liked }) => {
  return liked ? (
    <ThumbUp onClick={onClick} htmlColor={appColors.primary} />
  ) : (
    <ThumbUpOutlined onClick={onClick} />
  );
};

const CommentArea = ({ threadChats, avatar }) => {
  return (
    <div className={styles.commentArea}>
      <div className={styles.commentInput}>
        <Avatar className={styles.commentInputAvatar} src={avatar} rounded />
        <Input
          className={styles.commentInputBox}
          placeholder="Write Your comment"
        />
        <Send cursor="pointer" htmlColor={appColors.primary} />
      </div>
      <div className={styles.commentThreads}>
        {threadChats.map((thread) => (
          <CommentThread key={thread.id} {...thread} />
        ))}
      </div>
    </div>
  );
};

const CommentThread = (props) => {
  const { main, replies } = props;
  const { name, avatar, message, createdAt } = main;

  return (
    <div className={styles.commentThread}>
      <Avatar src={avatar} />
      <div className={styles.commentThreadColumn}>
        <div className={styles.comment}>
          <Text small css={{ fontWeight: "bold" }}>
            {name}
          </Text>
          <Text small>{message}</Text>
        </div>
        <div className={styles.commentInteraction}>
          <Text size={12} css={{ cursor: "pointer" }}>
            Like
          </Text>
          <Text size={12} css={{ cursor: "pointer" }}>
            Reply
          </Text>
          <Text size={12}>10min</Text>
        </div>
        <div className={styles.replies}>
          {replies.map((reply) => (
            <Comment key={reply.id} {...reply} />
          ))}
        </div>
      </div>
    </div>
  );
};

const Comment = (props) => {
  const { name, avatar, message, createdAt } = props;

  return (
    <div className={styles.commentThread}>
      <Avatar src={avatar} />
      <div className={styles.commentThreadColumn}>
        <div className={styles.comment}>
          <Text small css={{ fontWeight: "bold" }}>
            {name}
          </Text>
          <Text small>{message}</Text>
        </div>
        <div className={styles.commentInteraction}>
          <Text size={12} css={{ cursor: "pointer" }}>
            Like
          </Text>
          <Text size={12} css={{ cursor: "pointer" }}>
            Reply
          </Text>
          <Text size={12}>10min</Text>
        </div>
      </div>
    </div>
  );
};

interface PostRatingArea {
  locationRating: number;
  serviceRating: number;
  cleanlinessRating: number;
  valueRating: number;
}
