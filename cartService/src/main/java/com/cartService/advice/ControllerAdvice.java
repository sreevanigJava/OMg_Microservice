package com.cartService.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cartService.exception.CustomException;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(CustomException.class)
	public Map<String, String> handlingMethod(CustomException customException)
	{
		Map<String, String> map= new HashMap<String , String>();
		map.put("errormessage", customException.getMessage());
		return map;
		
		
	}

}
