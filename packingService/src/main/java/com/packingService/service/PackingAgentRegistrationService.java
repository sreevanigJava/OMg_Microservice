package com.packingService.service;

import java.util.List;

import com.packingService.entity.Dispatcher;
import com.packingService.entity.OrdersEntity;

public interface PackingAgentRegistrationService {

	public Dispatcher dispatched(Dispatcher dispatcher);

	public List<Dispatcher> dispatcher(String orderDate);

}
