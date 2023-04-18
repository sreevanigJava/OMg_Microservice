package com.cartService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cartService.entity.MyCart;
import com.cartService.entity.ProductsEntity;
public interface MyCartDao extends JpaRepository<MyCart, String>{
	
    MyCart findByCartId(String cartId);
	//boolean existsByEntity(ProductsEntity productsEntity);

	List<MyCart> findByUserName(String userId);
	void deleteById(String itemId);
	boolean existsByCartId(String cartId);
	
}
