import React from 'react';
import styles from './styles.module.css';

export default function PrimaryButton({name}) {

	return (
		<div className={styles.primaryButton}>
			<p>{name}</p>
		</div>
	);
}
