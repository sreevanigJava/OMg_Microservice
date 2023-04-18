package com.productService.service;

import java.util.List;

import com.productService.dto.CategoriesDto;
import com.productService.entity.CategoriesEntity;
import com.productService.entity.Inventory;

public interface CategoriesService {
	
	 Boolean addCategory(CategoriesDto categoriesDto);
	
	 List <CategoriesEntity> getAllCategories();
	Inventory addInventory(Inventory inventory);
	List<Inventory> getAllInventory();
	

}
