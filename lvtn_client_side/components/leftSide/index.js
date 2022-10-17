import { Twitter } from '@material-ui/icons';
import React from 'react';
import { PrimaryButton } from '../mocules';
import styles from './styles.module.css';
import {
  AccountCircleRounded,
  ExitToAppRounded,
  ExploreRounded,
  HomeRounded,
  TranslateRounded,
  WebRounded,
  WhatshotRounded,
} from '@material-ui/icons';
import appPages from '../../shared/appPages';

export default function LeftSide(props) {
  const { currentPage } = props;

  console.log(props);

  const menuItems = [
    {
      id: 0,
      icon: <HomeRounded />,
      name: 'FEED',
      focus: currentPage == appPages.home,
    },
    {
      id: 1,
      icon: <AccountCircleRounded />,
      name: 'PROFILE',
      focus: currentPage == appPages.profile,
    },
    {
      id: 2,
      icon: <ExploreRounded />,
      name: 'EXPLORE',
      focus: currentPage == appPages.explore,
    },
    {
      id: 3,
      icon: <TranslateRounded />,
      name: 'LANGUAGE',
      focus: currentPage == appPages.language,
    },
    {
      id: 4,
      icon: <ExitToAppRounded />,
      name: 'LOGOUT',
      focus: currentPage == appPages.logout,
    },
    {
      id: 5,
      icon: <WebRounded />,
      name: 'PAGES',
      focus: currentPage == appPages.pages,
    },
    {
      id: 6,
      icon: <WhatshotRounded />,
      name: 'TRENDING',
      focus: currentPage == appPages.trending,
    },
  ];

  return (
    <div className={styles.menu}>
      <div className={styles.fixedArea}>
        <div className={styles.iconContainer}>
          <Twitter style={{ color: 'rgb(101, 165, 255)' }} fontSize="large" />
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
        <PrimaryButton name="SIGN IN +" />
      </div>
    </div>
  );
}
