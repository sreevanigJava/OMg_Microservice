package com.profileservice.entity;

import lombok.Data;

@Data
public class ChangePasswordEntity {
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	

}
