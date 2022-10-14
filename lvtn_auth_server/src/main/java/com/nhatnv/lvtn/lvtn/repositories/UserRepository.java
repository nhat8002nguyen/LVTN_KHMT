package com.nhatnv.lvtn.lvtn.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nhatnv.lvtn.lvtn.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
