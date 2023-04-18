package com.packingService.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packingService.entity.PackingDepart;


public interface PackingDepartDao extends JpaRepository<PackingDepart, Integer> {
	PackingDepart findByorderedDate(LocalDate datee);
}
