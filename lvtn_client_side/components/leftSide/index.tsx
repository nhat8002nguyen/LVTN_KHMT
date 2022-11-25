import {
  AccountCircleRounded,
  ExitToAppRounded,
  ExploreRounded,
  HomeRounded,
  TranslateRounded,
  Twitter,
  WebRounded,
  WhatshotRounded,
} from "@material-ui/icons";
import GoogleIcon from "@mui/icons-material/Google";
import { signIn, signOut, useSession } from "next-auth/react";
import { useEffect, useState } from "react";
import appPages from "../../shared/appPages";
import GlobalButton from "../atoms/GlobalButton";
import styles from "./styles.module.css";

const createMenuItems = (currentPage) => [
  {
    id: 0,
    icon: <HomeRounded />,
    name: "FEED",
    focus: currentPage == appPages.home,
  },
  {
    id: 1,
    icon: <AccountCircleRounded />,
    name: "PROFILE",
    focus: currentPage == appPages.profile,
  },
  {
    id: 2,
    icon: <ExploreRounded />,
    name: "EXPLORE",
    focus: currentPage == appPages.explore,
  },
  {
    id: 3,
    icon: <TranslateRounded />,
    name: "LANGUAGE",
    focus: currentPage == appPages.language,
  },
  {
    id: 4,
    icon: <ExitToAppRounded />,
    name: "LOGOUT",
    focus: currentPage == appPages.logout,
  },
  {
    id: 5,
    icon: <WebRounded />,
    name: "PAGES",
    focus: currentPage == appPages.pages,
  },
  {
    id: 6,
    icon: <WhatshotRounded />,
    name: "TRENDING",
    focus: currentPage == appPages.trending,
  },
];

export default function LeftSide(props) {
  const { currentPage } = props;

  const { data: session, status: sessionStatus } = useSession();

  const [menuItems, setMenuItems] = useState(() =>
    createMenuItems(currentPage)
  );
  const [signInButtonText, setSignInButtonText] = useState(
    "Sign In With Google"
  );

  useEffect(() => {
    if (sessionStatus == "authenticated") {
      setSignInButtonText(session.user.name);
    }
    console.log(session);
  }, [sessionStatus]);

  const onGoogleSignInButtonPress = () => {
    if (sessionStatus == "loading") {
      return;
    }
    if (sessionStatus == "authenticated") {
      //TODO: navigate to profile page
      signOut();
      return;
    }
    signIn("google");
  };

  return (
    <div className={styles.menu}>
      <div className={styles.fixedArea}>
        <div className={styles.iconContainer}>
          <Twitter style={{ color: "rgb(101, 165, 255)" }} fontSize="large" />
        </div>
        <div className={styles.menuItemList}>
          {menuItems.map((item) => (
            <div
              key={item.id}
              className={!item.focus ? styles.menuItem : styles.menuItemFocus}
            >
              {item.icon}
              <p>{item.name}</p>
            </div>
          ))}
        </div>
        <GlobalButton
          icon={<GoogleIcon fontSize="small" />}
          text={signInButtonText}
          onPress={() => onGoogleSignInButtonPress()}
          loading={sessionStatus == "loading"}
        />
      </div>
    </div>
  );
}
