package com.productService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productService.entity.CategoriesEntity;

public interface AdminDashBoardDao extends JpaRepository<CategoriesEntity, String> {
	
	 boolean existsByCatName(String catName);
		CategoriesEntity findByCatName(String catName);

}
