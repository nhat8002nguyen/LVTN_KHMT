import { AccountCircleRounded, AddCircleRounded } from '@material-ui/icons';
import React from 'react';
import styles from './styles.module.css';

export default function UserStatusInput() {
  return (
    <form className={styles.userStatusInput}>
      <AccountCircleRounded className={styles.avatarIcon} />
      <input placeholder="What's on your mind." />
      <AddCircleRounded className={styles.addIcon} />
    </form>
  );
}
