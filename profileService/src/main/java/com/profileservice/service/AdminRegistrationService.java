package com.profileservice.service;

import com.profileservice.dto.AdminRegistrationDto;
import com.profileservice.entity.AdminRegistrationEntity;

public interface AdminRegistrationService {
	public AdminRegistrationEntity saveUser(AdminRegistrationDto adminRegistrationDto);
	public boolean validateOTP(String phoneNum, Integer otpNumber);
}
