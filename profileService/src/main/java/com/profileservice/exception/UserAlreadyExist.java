package com.profileservice.exception;

public class UserAlreadyExist extends RuntimeException {
	
	public UserAlreadyExist(String message)
	{
		super(message);
	}

}
