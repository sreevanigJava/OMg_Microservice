package com.cartService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cartService.entity.MyCartLists;

public interface MyCartListDao extends JpaRepository<MyCartLists, String> {

	MyCartLists findByUserName(String userName);

	boolean existsByUserName(String userName);

	void deleteByUserName(String userName);

}
