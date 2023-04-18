package com.inventoryService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryService.entity.Inventory;

public interface InventoryDao extends JpaRepository<Inventory, String> {

	boolean existsByProductName(String productName);

	Inventory findByProductName(String orderProductName);

}
