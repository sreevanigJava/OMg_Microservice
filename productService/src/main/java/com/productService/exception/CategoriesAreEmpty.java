package com.productService.exception;

public class CategoriesAreEmpty extends RuntimeException {
	
	public CategoriesAreEmpty(String message)
	{
		super(message);
	}

}
