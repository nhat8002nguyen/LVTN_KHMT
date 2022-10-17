import React from 'react'
import { followingStatus as status } from '../../../constants/homeConstants';

export default function FollowButton({followingStatus}) {

	const buttonStyles = {
		minHeight: "1rem",
		minWidth: "6rem",
		color: "white",
		borderRadius: "1rem",
		padding: "0.5rem 1rem",
		cursor: "pointer",
		fontSize: "12px",
		textAlign: "center",
	}

	const statusStyles = (followingStatus == status.FOLLOWING || followingStatus == status.PENDING)
		? { backgroundColor: "#005FF9", color: "white" } 
		: { backgroundColor: "white", color: "#005FF9", border: "1px solid #005FF9"};

	const getButtonName = () => {
		const buttonNames = {
			[status.NOT_FOLLOW]: "+ Follow",
			[status.FOLLOWING]: "Following",
			[status.PENDING]: "Pending...",
		}
		return buttonNames[followingStatus] ?? buttonNames[0];
	}

	return (
		<div style={{...buttonStyles, ...statusStyles}}>
			{getButtonName()}
		</div>
	);
}
