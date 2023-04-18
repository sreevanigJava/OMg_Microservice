package com.orderService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.orderService.entity.MyCartLists;

@FeignClient(name="CARTSERVICE",path="/api")
public interface CartFeign {
	@GetMapping("/user/getMyCartList")
	public MyCartLists getCart(@RequestHeader String userName);
	@DeleteMapping("/user/delete")
	public void delete(MyCartLists myCartLists);

}
