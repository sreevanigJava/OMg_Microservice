package com.packingService.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.packingService.exception.InvalidEnteredPassword;
import com.packingService.exception.NotValidOTPException;
import com.packingService.exception.UserAlreadyExist;

@RestControllerAdvice
public class ExceptionHandlers {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField() , error.getDefaultMessage());
		});
		return errorMap;
	}
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserAlreadyExist.class)
	public Map<String, String> handleBusinessException(UserAlreadyExist userAlredayExist) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", userAlredayExist.getMessage());
		return errorMap;
	}
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NotValidOTPException.class)
	public Map<String, String> handleBusinessException(NotValidOTPException notValidOTPException) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", notValidOTPException.getMessage());
		return errorMap;
	}
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(InvalidEnteredPassword.class)
	public Map<String, String>handleBussinessException(InvalidEnteredPassword invalidEnteredPassword)
	{
		Map<String, String>hashMap=new HashMap<String, String>();
		hashMap.put("errorMessage",invalidEnteredPassword.getMessage());
		return hashMap;
	}
	
}
