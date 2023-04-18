package com.profileservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.profileservice.dto.RegistrationDto;
import com.profileservice.entity.AddressEntity;
import com.profileservice.entity.RegistrationEntity;
import com.profileservice.exception.UserAlreadyExist;
@Service
public interface RegistationService {
	RegistrationEntity saveUser(RegistrationDto registrationdto) throws UserAlreadyExist;
	 ResponseEntity<RegistrationEntity> getMyProfile(@RequestHeader String userName);
	 RegistrationEntity updateUser(RegistrationEntity registrationEntity);
		List<AddressEntity> getMyAddress(String userName);
}
