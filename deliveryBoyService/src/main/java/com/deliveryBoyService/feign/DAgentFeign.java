package com.deliveryBoyService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="DELIVERYAGENTSERVICE",path="/api/dAdmin")
public interface DAgentFeign {
	@PostMapping("/existByPhnNum")
	public boolean checkPhn(@RequestParam String phnNum);
}
