package com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.user_repository;

import org.springframework.data.repository.CrudRepository;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.UserEntity;

public interface JPAUserRepository extends CrudRepository<UserEntity, Long> {

}
