package com.cartService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cartService.entity.ProductsEntity;

@FeignClient(name="PRODUCTSERVICE",path="/api/products")
public interface ProductEntityFeign {
	@GetMapping("/getProdByProdId/{prodId}")
	public ProductsEntity getProdById(@PathVariable("prodId") String prodId);

}
