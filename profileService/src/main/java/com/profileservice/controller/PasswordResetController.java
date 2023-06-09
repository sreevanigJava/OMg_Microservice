package com.profileservice.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.profileservice.entity.Otp;
import com.profileservice.entity.SmsEntity;
import com.profileservice.service.OtpService;
import com.profileservice.service.SmsService;

@RestController
@RequestMapping("/api/password")
@CrossOrigin("*")
public class PasswordResetController {

	@Autowired
	private SimpMessagingTemplate webSocket;
	@Autowired
	private SmsService service;
	private final String TOPIC_DESTINATION = "/lesson/sms";
	
	@Autowired
	private OtpService otpService;
	
	@PostMapping("/sendOtp")
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

	@PostMapping("/verify")
	public ResponseEntity<String> verifyOtp(@RequestBody Otp otp) 
	{
		Integer userotp=Integer.parseInt(otp.getOtp());
		
		if(otpService.validateOTP(otp.getNewPassword(), userotp))
		{
			return new ResponseEntity<>("Password has been Changed", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("OTP is not Valid", HttpStatus.INTERNAL_SERVER_ERROR);

		
	}
}
