package com.packingService.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packingService.entity.Dispatcher;

public interface DispatcherDao extends JpaRepository<Dispatcher, Integer> {
    List<Dispatcher> findByOrderDate(String orderDate);
}
