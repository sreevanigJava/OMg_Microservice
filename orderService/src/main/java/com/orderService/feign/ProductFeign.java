package com.orderService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.orderService.entity.MyCartLists;
import com.orderService.entity.ProductsEntity;
@FeignClient(name= "PRODUCTSERVICE",path="/api/admin")
public interface ProductFeign {

	@GetMapping("/getProdByProdId/{prodId}")
	ProductsEntity getProdById(@PathVariable String prodId);


}
