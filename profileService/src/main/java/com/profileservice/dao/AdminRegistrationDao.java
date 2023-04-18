package com.profileservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profileservice.entity.AdminRegistrationEntity;


public interface AdminRegistrationDao extends JpaRepository<AdminRegistrationEntity, Integer> {
	AdminRegistrationEntity findByUserName(String useName);
	boolean existsByUserName(String userName);
	boolean existsByPhoneNumber(String userName);
	AdminRegistrationEntity findByPhoneNumber(String phoneNumber);
	
}
