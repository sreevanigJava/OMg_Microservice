package com.profileservice.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profileservice.dao.RegistrationDao;
import com.profileservice.dto.Authorities;
import com.profileservice.dto.JwtToken;
import com.profileservice.dto.LoginRequest;
import com.profileservice.entity.CurrentUserDetails;
import com.profileservice.entity.RegistrationEntity;
import com.profileservice.exception.CustomException;
import com.profileservice.jwt.JwtUtil;


@RestController()
@RequestMapping("/api")
@CrossOrigin("*")
public class LoginController {

	//@Autowired
	//private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	//@Autowired
	//private UserDetailsService securityUserDetailsService;

	@Autowired
	RegistrationDao registrationDao;

	@PostMapping("/login")
//	@CircuitBreaker(name="PROFILE-SERVICE", fallbackMethod = "loginFallback")
	public ResponseEntity<CurrentUserDetails> login(
			@RequestBody LoginRequest loginRequest,
			CurrentUserDetails currentUserDetails) throws Exception {
	
		
		RegistrationEntity entity=	registrationDao.findByUserName(loginRequest.getUserName());

		try {

			if(!passwordEncoder.matches(loginRequest.getPassword(), entity.getPassword()))
				{
		         	throw new CustomException("invalid username or password");
				}
			
		} catch (Exception e) {
			throw new CustomException("invalid username or password");
		}
		JwtToken jwtToken = new JwtToken();
		jwtToken.setJwtToken(jwtUtil.generateToken(loginRequest.getUserName()));

		RegistrationEntity registrationEntity = registrationDao.findByUserName(loginRequest.getUserName());
		currentUserDetails.setUserName(registrationEntity.getUserName());

	     Iterator iterator=registrationEntity.getAuthorities().iterator();
	    
	     
	    	 Authorities authorities=(Authorities) iterator.next();
	    	 currentUserDetails.setRole(authorities.getRole());
	     
	     

		// currentUserDetails.setAuthority();
		currentUserDetails.setToken(jwtToken);
		
		return new ResponseEntity<CurrentUserDetails>(currentUserDetails, HttpStatus.OK);

	}
	

	
}