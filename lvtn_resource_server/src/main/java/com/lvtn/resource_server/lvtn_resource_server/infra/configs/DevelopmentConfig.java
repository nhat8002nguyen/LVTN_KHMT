package com.lvtn.resource_server.lvtn_resource_server.infra.configs;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.hotel_repository.JPAHotelRepository;
import com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.post_repository.JPAPostRepository;
import com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.user_repository.JPAUserRepository;

@Configuration
public class DevelopmentConfig {
	@Bean
	public ApplicationRunner dataLoader(
			PasswordEncoder encoder,
			JPAPostRepository postRepository,
			JPAUserRepository userRepository,
			JPAHotelRepository hotelRepository) {
		return args -> {
			// UserEntity user1 = new UserEntity(0, "Nhat", "Nguyen",
			// "aominenguyendu@gmail.com", encoder.encode("password"),
			// "0946081581", "This a user about",
			// "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/640px-Image_created_with_a_mobile_phone.png");

			// userRepository.save(user1);

			// Set<PostImageEntity> postImages = new HashSet<>();
			// postImages.add(new PostImageEntity(0,
			// "https://pix10.agoda.net/hotelImages/2817185/-1/4406a970306a452300f94532410dab2c.jpg"));

			// postImages.add(new PostImageEntity(0,
			// "https://pix10.agoda.net/hotelImages/2817185/-1/4406a970306a452300f94532410dab2c.jpg"));

			// HotelEntity hotel = new HotelEntity(0, "Khach san", "Cong Hoa, quan Tan Binh,
			// HCM", "Something about this hotel",
			// 4.7);

			// hotelRepository.save(hotel);

			// ServiceEvaluationPostEntity entity = new
			// ServiceEvaluationPostEntity(123456789, user1,
			// "Review khach san Riverside", "Khach san nhu cai quan que y", postImages,
			// hotel, 4.5, 4.5, 4.5, 4.9, 100, 20,
			// 10, PostVisibility.PUBLIC,
			// null, null);
			// ServiceEvaluationPostEntity entity1 = new
			// ServiceEvaluationPostEntity(1234567, user1,
			// "Review khach san Riverside", "Khach san nhu cai quan que y", postImages,
			// hotel, 4.5, 4.5, 4.5, 4.9, 100, 20,
			// 10, PostVisibility.PUBLIC,
			// null, null);

			// postRepository.saveAll(Arrays.asList(entity, entity1));
		};
	}
}
