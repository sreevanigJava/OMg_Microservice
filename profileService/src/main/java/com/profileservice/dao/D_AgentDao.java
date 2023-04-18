package com.profileservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profileservice.entity.D_AgentEntity;

public interface D_AgentDao extends JpaRepository<D_AgentEntity, Integer>{
	D_AgentEntity findByuserName(String userName);
	Boolean existsByuserName(String userName);
	Boolean existsByphoneNumber(String phoneNumber);
	D_AgentEntity findByphoneNumber(String phoneNumber);
}
