import React from 'react';
import styles from './styles.module.css';
import { SearchOutlined } from '@material-ui/icons';


export default function SearchInput() {

	return (
		<form className={styles.searchContainer}>
			<SearchOutlined fontSize="medium" className={styles.searchIcon}/>
			<input className={styles.searchInput} type="text" placeholder="Search" />
		</form>
	);
}
