package com.profileservice.exception;

public class NotValidOTPException extends RuntimeException {
	
	public NotValidOTPException(String message)
	{
		super(message);
	}

}
