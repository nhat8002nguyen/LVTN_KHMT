-- CREATE TABLE users (
-- 	id BIGINT,
-- 	first_name VARCHAR(100) NOT NULL,
-- 	last_name VARCHAR(100) NOT NULL,
-- 	email VARCHAR(500) NOT NULL,
-- 	password VARCHAR(1000) NOT NULL,
-- 	phone VARCHAR(20),
-- 	about VARCHAR(1000),
-- 	image_url VARCHAR(1000),

-- 	PRIMARY KEY(id)
-- );

-- CREATE TABLE service_evaluation_posts (
-- 	id BIGINT KEY,
-- 	user_id BIGINT NOT NULL,
-- 	title VARCHAR(500),
-- 	body VARCHAR(10000) NOT NULL,
-- 	hotel VARCHAR(100),
-- 	location_rating FLOAT,
-- 	cleanliness_rating FLOAT,
-- 	service_rating FLOAT,
-- 	value_rating FLOAT,
-- 	liked_count INTEGER,
-- 	disliked_count INTEGER,
-- 	shared_count INTEGER,
-- 	created_at TIMESTAMP,
-- 	updated_at TIMESTAMP,

-- 	FOREIGN KEY (user_id) REFERENCES users(id) 
-- );

-- CREATE TABLE post_images (
-- 	id VARCHAR(50) KEY,
-- 	post_id BIGINT,
-- 	url VARCHAR(1000),

-- 	FOREIGN KEY (post_id) REFERENCES service_evaluation_posts(id)
-- );

-- CREATE TABLE room_features (
-- 	id BIGINT KEY,
-- 	icon VARCHAR(500),
-- 	description VARCHAR(100)
-- );


-- CREATE TABLE room_features_evaluation (
-- 	service_evaluation_id BIGINT,
-- 	room_feature_id BIGINT,

-- 	PRIMARY KEY(service_evaluation_id, room_feature_id),
-- 	FOREIGN KEY (service_evaluation_id) REFERENCES service_evaluation_posts(id),
-- 	FOREIGN KEY (room_feature_id) REFERENCES room_features(id)
-- );

-- CREATE TABLE property_amenities (
-- 	id BIGINT KEY,
-- 	icon VARCHAR(1000),
-- 	description VARCHAR(100)
-- );

-- CREATE TABLE property_amenities_evaluation (
-- 	service_evaluation_id BIGINT,
-- 	property_amenity_id BIGINT,

-- 	PRIMARY KEY(service_evaluation_id, property_amenity_id),
-- 	FOREIGN KEY (property_amenity_id) REFERENCES property_amenities(id),
-- 	FOREIGN KEY (service_evaluation_id) REFERENCES service_evaluation_posts(id)
-- );


-- INSERT INTO users (id, first_name, last_name, email, password, phone, about)
-- VALUES (
-- 	1234, 
-- 	"Nhat", 
-- 	"Nguyen", 
-- 	"123@gmail.com", 
-- 	"abcmdkghck39485jgkdcks9d9g9a", 
-- 	"0946081581", 
-- 	"This something about me",
-- 	"https://d5nunyagcicgy.cloudfront.net/external_assets/hero_examples/hair_beach_v391182663/original.jpeg",
-- );

-- INSERT INTO users (id, first_name, last_name, email, password, phone, about)
-- VALUES (
-- 	12345, 
-- 	"Nhat", 
-- 	"Nguyen", 
-- 	"123@gmail.com", 
-- 	"abcmdkghck39485jgkdcks9d9g9a", 
-- 	"0946081581", 
-- 	"This something about me",
-- 	"https://d5nunyagcicgy.cloudfront.net/external_assets/hero_examples/hair_beach_v391182663/original.jpeg",
-- );