package com.packingService.service;

import java.util.List;


//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packingService.dao.DispatcherDao;
import com.packingService.entity.Dispatcher;

@Service
public class PackingAgentRegistrationServiceImpl implements PackingAgentRegistrationService{

@Autowired 
DispatcherDao dispatcherDao;


 // private static Logger logger = Logger.getLogger(PackingAgentRegistrationServiceImpl.class);

@Override
public Dispatcher dispatched(Dispatcher dispatcher) {

	return dispatcherDao.save(dispatcher);
}

@Override
public List<Dispatcher> dispatcher(String orderDate) {
	
	List<Dispatcher> list2 = dispatcherDao.findByOrderDate(orderDate);
	return list2;
}

	
	}

