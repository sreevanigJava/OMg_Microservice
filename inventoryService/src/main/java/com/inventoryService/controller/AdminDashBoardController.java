package com.inventoryService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryService.entity.Inventory;
import com.inventoryService.service.CategoriesServiceImpl;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/inventory")
public class AdminDashBoardController {

	@Autowired
	CategoriesServiceImpl categoriesServiceImpl;

	@PostMapping("/addInventory")
	public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {

		return new ResponseEntity(categoriesServiceImpl.addInventory(inventory), HttpStatus.OK);
	}

	@GetMapping("/getAllInventory")
	public ResponseEntity<Inventory> getAllInventory() {
		return new ResponseEntity(categoriesServiceImpl.getAllInventory(), HttpStatus.OK);
	}
	@PostMapping("/updateInventory")
	public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory)
	{
		return new ResponseEntity<Inventory>(categoriesServiceImpl.updateInventory(inventory),HttpStatus.OK);
		
	}
}