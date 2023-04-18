package com.deliveryBoyService.dao;


import org.springframework.data.repository.CrudRepository;

import com.deliveryBoyService.entity.ReturnedOrders;

public interface ReturnedOrdersDao extends CrudRepository<ReturnedOrders,Integer>{

}
