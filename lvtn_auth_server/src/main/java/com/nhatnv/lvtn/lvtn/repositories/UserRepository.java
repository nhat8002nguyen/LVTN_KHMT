package com.nhatnv.lvtn.lvtn.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nhatnv.lvtn.lvtn.Entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
