import { imageUrlAlt } from "@/constants/homeConstants";
import { appColors } from "@/shared/theme";
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
import { Avatar, Card, Input, Text } from "@nextui-org/react";
import Image from "next/image";
import React from "react";
import styles from "./styles.module.css";

export default function Post(props) {
  const {
    stableData: {
      avatar,
      name,
      identified,
      tagName,
      createdAt,
      descriptions,
      images,
    },
    unstableData: { numLikeds, numTexts, numShareds, threadChats },
  } = props;

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

    let date = new Date(createdAt);
    return date.getDate() + " " + monthNames[date.getMonth()];
  };

  return (
    <Card
      css={{ minHeight: "30rem", maxWidth: "40rem", backgroundColor: "white" }}
    >
      <div className={styles.postContainer}>
        <Avatar src={avatar} rounded />
        <div className={styles.postMain}>
          <div className={styles.header}>
            <div className={styles.headerLeft}>
              <Text css={{ fontWeight: "bold" }}>{name}</Text>
              {identified == 1 ? (
                <CheckCircle color="primary" fontSize="small" />
              ) : null}
              <Text css={{ fontSize: "small" }}>{tagName}</Text>
            </div>
            <div className={styles.headerRight}>
              <Text css={{ fontSize: "small" }}>{getDayMonth()}</Text>
              <MoreVertRounded />
            </div>
          </div>
          <div className={styles.content}>
            <div className={styles.descriptions}>
              {descriptions.map((des, i) => (
                <Text css={des.styles} key={i}>
                  {des.text}
                </Text>
              ))}
            </div>
            <PostImages images={images} />
            <InteractionMetrics
              numLikeds={numLikeds}
              numTexts={numTexts}
              numShareds={numShareds}
            />
            <CommentArea avatar={avatar} threadChats={threadChats} />
          </div>
        </div>
      </div>
    </Card>
  );
}

const PostImages = ({ images }) => {
  if (
    images.first != null &&
    images.second.length == 0 &&
    images.third.length == 0
  ) {
    return (
      <Image
        style={{ cursor: "pointer" }}
        src={images.first}
        alt={imageUrlAlt.postAlt}
        width={450}
        height={300}
      />
    );
  }

  if (
    images.first != null &&
    images.second.length != 0 &&
    images.third.length == 0
  ) {
    return (
      <div>
        <Image
          style={{ cursor: "pointer" }}
          src={images.first}
          alt={imageUrlAlt.postAlt}
          width={225}
          height={225}
        />
        <Image
          style={{ cursor: "pointer" }}
          src={images.second}
          alt={imageUrlAlt.postAlt}
          width={225}
          height={225}
        />
      </div>
    );
  }

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
