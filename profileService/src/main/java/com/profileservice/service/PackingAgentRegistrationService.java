package com.profileservice.service;

import com.profileservice.dto.PackingAgentRegistrationDto;
import com.profileservice.entity.PackingAgentRegistrationEntity;

public interface PackingAgentRegistrationService {
	public PackingAgentRegistrationEntity saveUser(PackingAgentRegistrationDto packingAgentRegistrationDto);
	public boolean validateOTP(String phoneNum,Integer otpNumber);
}
