import { Avatar, Button, Card, Text } from '@nextui-org/react';
import { followingStatus as friendStatus } from '@/constants/homeConstants.js';
import { CardTitleText, SmallGreyText } from '@/components/atoms/appTexts';

export default function PersonItem(props) {
  const { id, mediaUrl, name, position, followingStatus } = props;

  const getLetterColor = () => {
    return followingStatus == friendStatus.FOLLOWING ? 'white' : '#005FF9';
  };

  const getBackgroundColor = () => {
    return followingStatus == friendStatus.FOLLOWING ? '#005FF9' : 'white';
  };

  const getButtonName = () => {
    return followingStatus == friendStatus.FOLLOWING ? 'Following' : '+ Follow';
  };

  return (
    <Card
      key={id}
      color="white"
      css={{
        mh: '$50',
        w: '$35',
        alignItems: 'center',
        backgroundColor: 'white',
      }}
    >
      <Avatar rounded src={mediaUrl} />
      <CardTitleText text={name} styles={{ marginTop: '1rem' }} />
      <SmallGreyText text={position} />
      <Button
        size="xs"
        color={'white'}
        css={{
          maxW: '$10',
          backgroundColor: getBackgroundColor(),
          border: '1px solid #005FF9',
          color: getLetterColor(),
          marginTop: '$10',
          padding: '$5',
        }}
      >
        {getButtonName()}
      </Button>
    </Card>
  );
}
