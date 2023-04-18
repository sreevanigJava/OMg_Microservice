package com.apiGateway.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
@ResponseStatus(HttpStatus.BAD_REQUEST)
public void customException()
{
	
}
}
