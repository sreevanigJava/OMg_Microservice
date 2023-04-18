package com.orderService.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orderService.entity.RegistrationEntity;

@Repository
public interface RegistrationDao extends CrudRepository<RegistrationEntity, Integer> {

	RegistrationEntity findByUserName(String userName);
	boolean existsByUserName(String userName);
	boolean existsByPhoneNumber(String userName);
	RegistrationEntity findByPhoneNumber(String phoneNumber);

}
