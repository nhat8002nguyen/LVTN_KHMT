import React from 'react';
import { Text } from '@nextui-org/react';

export const SmallGreyText = ({ text, styles }) => {
  return (
    <Text h6 size={12} color="grey" css={styles}>
      {text}
    </Text>
  );
};

export const CardTitleText = ({ text, styles }) => {
  return (
    <Text h6 size={14} color="black" css={styles}>
      {text}
    </Text>
  );
};

export const HashTagText = ({ text, styles }) => {
  return (
    <Text h6 size={14} color="blue" css={styles}>
      {text}
    </Text>
  );
};
