package com.profileservice.entity;

import com.profileservice.dto.JwtToken;

import lombok.Data;

@Data
public class CurrentUserDetails {
	
	private String userName;
	private String role;
	private JwtToken token;

}
