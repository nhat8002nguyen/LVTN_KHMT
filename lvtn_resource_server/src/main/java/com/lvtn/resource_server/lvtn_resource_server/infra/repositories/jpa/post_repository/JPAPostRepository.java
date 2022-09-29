package com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.post_repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.service_evaluation_post.ServiceEvaluationPost;

public interface JPAPostRepository extends PagingAndSortingRepository<ServiceEvaluationPost, Long> {

}
