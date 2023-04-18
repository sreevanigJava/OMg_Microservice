package com.profileservice.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profileservice.dto.ChangePasswordDto;
import com.profileservice.dto.PackingAgentRegistrationDto;
import com.profileservice.entity.PackingAgentRegistrationEntity;
import com.profileservice.entity.SmsEntity;
import com.profileservice.service.PackingAgentRegistrationServiceImpl;
import com.profileservice.service.SmsService;

@RestController
@RequestMapping("/api/pAgentRegister")
@CrossOrigin("*")
public class PackingAgentRegistrationController {
	private PackingAgentRegistrationServiceImpl packingAgentRegistrationServiceImpl;
	
	public PackingAgentRegistrationController(PackingAgentRegistrationServiceImpl packingAgentRegistrationServiceImpl) {
		super();
		this.packingAgentRegistrationServiceImpl = packingAgentRegistrationServiceImpl;
	}
	@Autowired
	private SimpMessagingTemplate webSocket;
	@Autowired
	private SmsService service;
	
	private final String TOPIC_DESTINATION = "/lesson/sms";

	@PostMapping("/P_Agent/sendOtp")
	public ResponseEntity<Boolean> smsSubmit(@RequestBody SmsEntity sms) {
		try {

			service.send(sms);

		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent !: " + sms.getPhoneNumber());

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	private String getTimeStamp() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	}
	
	@PostMapping("/P_Agent/register")
	public ResponseEntity<PackingAgentRegistrationEntity> createUser(@RequestBody @Valid PackingAgentRegistrationDto packingAgentRegistrationDto )
	{
		return new ResponseEntity(packingAgentRegistrationServiceImpl.saveUser(packingAgentRegistrationDto),HttpStatus.OK);
	}

	@GetMapping("/P_Agent/myProfile")
	public ResponseEntity<PackingAgentRegistrationEntity> getMyProfile(@RequestHeader String userName) {
		//String userName = packingAgentRegistrationServiceImpl.getMyToken(httpServletRequest);
		return new ResponseEntity(packingAgentRegistrationServiceImpl.getAgent(userName), HttpStatus.OK);
	}
	@PutMapping("/P_Agent/UPdate")
	public ResponseEntity<PackingAgentRegistrationEntity>update(@RequestBody @Valid PackingAgentRegistrationEntity packingAgentRegistrationEntity)
	{
	return new ResponseEntity(packingAgentRegistrationServiceImpl.Upadteprofile(packingAgentRegistrationEntity),HttpStatus.OK);
}
	@PutMapping("/P_Agent/password")
	public String changePassword(@RequestHeader String userName,@RequestBody ChangePasswordDto changepasswordDto,Authentication authentication)
	{
		//String username=packingAgentRegistrationServiceImpl.getMyToken(httpServletRequest);
		PackingAgentRegistrationEntity packingAgentRegistrationEntity=packingAgentRegistrationServiceImpl.getAgent(userName);
		boolean passwordChanged=packingAgentRegistrationServiceImpl.ChangePassword(packingAgentRegistrationEntity, changepasswordDto);
		if(passwordChanged)
		{
			return "Password has been Changed";
		}
		return "Error in changing password";
	}
}

