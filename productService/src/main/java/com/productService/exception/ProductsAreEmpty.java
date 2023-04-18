package com.productService.exception;

public class ProductsAreEmpty extends RuntimeException{
	
	public ProductsAreEmpty(String message)
	{
		super(message);
	}

}
