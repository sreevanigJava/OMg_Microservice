package com.orderService.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.orderService.entity.AddressEntity;
import com.orderService.entity.RegistrationEntity;
@FeignClient(name="PROFILESERVICE",path="/api")
public interface ProfileFeign {

	@GetMapping("/user/getMyAddress")
	List<AddressEntity> getMyAddress(@RequestHeader String userName);

	@GetMapping("/myProfile")
	RegistrationEntity getRegistrationEntity(@RequestHeader String userName);

}
