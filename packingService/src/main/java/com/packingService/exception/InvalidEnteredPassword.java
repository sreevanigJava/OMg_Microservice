package com.packingService.exception;

public class InvalidEnteredPassword extends RuntimeException {

	public InvalidEnteredPassword(String message)
	{
		super(message);
	}
}
