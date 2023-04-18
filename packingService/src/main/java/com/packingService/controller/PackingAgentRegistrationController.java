package com.packingService.controller;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.packingService.entity.Dispatcher;
import com.packingService.entity.OrdersEntity;
import com.packingService.feign.PackingFeign;
import com.packingService.service.PackingAgentRegistrationService;
import com.packingService.service.PackingAgentRegistrationServiceImpl;

@RestController
@RequestMapping("/api/packing")
@CrossOrigin("*")
public class PackingAgentRegistrationController {
	private PackingAgentRegistrationServiceImpl packingAgentRegistrationServiceImpl;
	
	public PackingAgentRegistrationController(PackingAgentRegistrationServiceImpl packingAgentRegistrationServiceImpl) {
		super();
		this.packingAgentRegistrationServiceImpl = packingAgentRegistrationServiceImpl;
	}
	@Autowired
	PackingAgentRegistrationService PackingAgentRegistrationServiceImpl;
	@Autowired
	PackingFeign packingFeign;
	
	private final String TOPIC_DESTINATION = "/lesson/sms";

	@PostMapping("/dipatch")
	public Dispatcher dispatch(@RequestBody Dispatcher dispatcher) {

		return PackingAgentRegistrationServiceImpl.dispatched(dispatcher);
	}
	
	@GetMapping("/dispatchedByDate")
	public List<Dispatcher> dispatchDate(@RequestParam String orderDate) {
		return PackingAgentRegistrationServiceImpl.dispatcher(orderDate);
	}
	@GetMapping("/getorderid")
	public OrdersEntity getorderid(@RequestParam String orderId) {
		return packingFeign.getorderid(orderId);
	}
}