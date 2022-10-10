package com.lvtn.resource_server.lvtn_resource_server.infra.repositories.jpa.hotel_repository;

import org.springframework.data.repository.CrudRepository;

import com.lvtn.resource_server.lvtn_resource_server.infra.entities.HotelEntity;

public interface JPAHotelRepository extends CrudRepository<HotelEntity, Long> {

}
