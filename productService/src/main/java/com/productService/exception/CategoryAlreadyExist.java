package com.productService.exception;

public class CategoryAlreadyExist extends RuntimeException {
	
	public CategoryAlreadyExist(String message)
	{
		super(message);
	}

}
