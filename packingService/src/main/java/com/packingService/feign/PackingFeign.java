package com.packingService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.packingService.entity.OrdersEntity;

@FeignClient(name="orderService",path="/api")
public interface PackingFeign {
	@GetMapping("/getorderid")
	public OrdersEntity getorderid(@RequestParam String orderId);
}
