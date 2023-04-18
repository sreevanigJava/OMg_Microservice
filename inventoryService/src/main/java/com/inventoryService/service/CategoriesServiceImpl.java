package com.inventoryService.service;

import java.util.List;



//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventoryService.dao.InventoryDao;
import com.inventoryService.entity.Inventory;
import com.inventoryService.exception.CustomException;

@Service
public class CategoriesServiceImpl implements CategoriesService {
	@Autowired
	private InventoryDao inventoryDao;

	//private static Logger logger = Logger.getLogger(CategoriesServiceImpl.class);


	public Inventory addInventory(Inventory inventory) {
	//	logger.info("Admin dash board add Inventory method started");
		boolean isProdExist = inventoryDao.existsByProductName(inventory.getProductName());
		if (isProdExist) {
			throw new CustomException("Product is already exist");
		} else if (inventory == null) {
			throw new CustomException("Please enter item details to add into inventory");
		}
		//logger.info("Admin dash board add Inventory method ended..");

		return inventoryDao.save(inventory);
	}

	public List<Inventory> getAllInventory() {
		//logger.info("Admin Dash Board start Inventory end");
		List<Inventory> inventory = inventoryDao.findAll();
		if (!inventory.isEmpty()) {
		//	logger.info("Admin Dash Board get all Inventory end");
			return inventory;
		} else
			throw new CustomException("Inventories are empty ");

	}

	@Override
	public Inventory updateInventory(Inventory inventory) {
		return inventoryDao.save(inventory);
	}

	
}
