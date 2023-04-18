package com.inventoryService.service;

import java.util.List;

import com.inventoryService.entity.Inventory;

public interface CategoriesService {

	Inventory addInventory(Inventory inventory);
	List<Inventory> getAllInventory();
	Inventory updateInventory(Inventory inventory);

}
