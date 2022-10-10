package com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.post_repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.ServiceEvaluationPostEntity;

public interface JPAPostRepository extends PagingAndSortingRepository<ServiceEvaluationPostEntity, Long> {
	List<ServiceEvaluationPostEntity> findByOrderByCreatedAtDesc(Pageable pageable);

	List<ServiceEvaluationPostEntity> findByUserIdOrderByCreatedAtDesc(long userId, Pageable page);
}
