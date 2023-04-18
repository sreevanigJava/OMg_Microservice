package com.orderService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.orderService.entity.Inventory;

@FeignClient(name="INVENTORYSERVICE",path="/api/admin")
public interface InventoryFeign {
	@GetMapping("/getAllInventory")
	Inventory getInventory(@PathVariable String orderProductName);
	@PostMapping("/updateInventory")
	Inventory updateInventory(Inventory inventory);
}
