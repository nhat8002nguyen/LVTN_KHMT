package com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.post_repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.ServiceEvaluationPostEntity;

public interface JPAPostRepository extends PagingAndSortingRepository<ServiceEvaluationPostEntity, Long> {
	List<ServiceEvaluationPostEntity> findByOrderByCreatedAtDesc();

	List<ServiceEvaluationPostEntity> findByOrderByCreatedAtDesc(Pageable pageable);

	List<ServiceEvaluationPostEntity> findByUserIdOrderByCreatedAtDesc(long userId, Pageable page);

	List<ServiceEvaluationPostEntity> findByUserUsernameOrderByCreatedAtDesc(String username, Pageable page);

	@Query(nativeQuery = true, value = "SELECT * from service_evaluation_posts s " +
			"WHERE s.user_id IN (" +
			"SELECT friends_id from users_friends " +
			"WHERE users_id = (" +
			"SELECT id FROM users WHERE username = :username)) " +
			"ORDER BY created_at DESC; ")
	List<ServiceEvaluationPostEntity> findByUserFriendsUsernameOrderByCreatedAtDesc(
			@Param(value = "username") String username);
}
