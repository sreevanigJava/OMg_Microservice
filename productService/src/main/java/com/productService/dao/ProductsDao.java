package com.productService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productService.entity.CategoriesEntity;
import com.productService.entity.ProductsEntity;

public interface ProductsDao extends JpaRepository<ProductsEntity, String> {
	boolean existsByProdName(String prodName);

	List<ProductsEntity> findByCategoriesEntity(CategoriesEntity categoriesEntity);

	ProductsEntity findByProdId(String prodId);

	ProductsEntity findByProdName(String prodName);

	boolean existsByProdId(String prodId);
	
}
