package com.profileservice.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

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

import com.profileservice.dto.AdressDto;
import com.profileservice.dto.RegistrationDto;
import com.profileservice.entity.AddressEntity;
import com.profileservice.entity.RegistrationEntity;
import com.profileservice.entity.SmsEntity;
import com.profileservice.service.RegistrationServiceImp;
import com.profileservice.service.SmsService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class RegistrationController {

	private RegistrationServiceImp registrationServiceImp;

	public RegistrationController(RegistrationServiceImp registrationServiceImp) {
		super();
		this.registrationServiceImp = registrationServiceImp;
	}

	@Autowired
	private SimpMessagingTemplate webSocket;
	@Autowired
	private SmsService service;  
	private final String TOPIC_DESTINATION = "/lesson/sms";

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

	@PostMapping("/register")
	public ResponseEntity<RegistrationEntity> createUser(@RequestBody @Valid RegistrationDto registrationdto) {

		return new ResponseEntity<>(registrationServiceImp.saveUser(registrationdto), HttpStatus.CREATED);
	}
	@GetMapping("/myProfile")
    public ResponseEntity<?> myProfile(@RequestHeader String userName ) {
        try {
            return registrationServiceImp.getMyProfile(userName);
        } catch (Exception e) {
            return new ResponseEntity<>("exception occured", HttpStatus.BAD_REQUEST);
        }
    
	}
	
	@PutMapping("/update")
	public ResponseEntity<RegistrationEntity> updateUser(@RequestBody @Valid RegistrationEntity registrationEntity) {

		return new ResponseEntity(registrationServiceImp.updateUser(registrationEntity), HttpStatus.CREATED);
	}
	@PostMapping("/addAddress")
	public ResponseEntity<AddressEntity> saveAddress(@RequestHeader String userName,
			@RequestBody AdressDto adressDto) {
		//String userName = registrationServiceImp.getMyToken(httpServletRequest);
		return new ResponseEntity<AddressEntity>(registrationServiceImp.saveAddress(userName, adressDto),
				HttpStatus.OK);
	}
	@GetMapping("/getMyAddress")
	public ResponseEntity<List<AddressEntity>> getMyAddress(@RequestHeader String userName)
	{
		return new ResponseEntity<>(registrationServiceImp.getMyAddress(userName), HttpStatus.OK);
	}
}
