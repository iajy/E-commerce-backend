package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Plant;

public interface PlantRepo extends JpaRepository<Plant,Integer> {
	
	List <Plant> findByOfferGreaterThan(double value);

}
