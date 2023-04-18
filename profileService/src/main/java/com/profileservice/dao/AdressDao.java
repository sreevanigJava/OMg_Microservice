package com.profileservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profileservice.entity.AddressEntity;

public interface AdressDao extends JpaRepository<AddressEntity, String> {

	List<AddressEntity> findByUserName(String userName);
	boolean existsByUserName(String userName);

}
