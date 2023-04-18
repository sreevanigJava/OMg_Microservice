package com.DeliveryService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DeliveryService.entity.DeliveryBoyOrders;

public interface DeliveryBoyOrdersDao extends JpaRepository<DeliveryBoyOrders, Integer>{

}
