package com.profileservice.service;

import com.profileservice.dto.D_AgentDto;
import com.profileservice.entity.D_AgentEntity;

public interface D_AgentService {
	public D_AgentEntity saveUser(D_AgentDto d_AgentDto);
	public boolean validateOTP(String phoneNum,Integer otpNumber);
}
