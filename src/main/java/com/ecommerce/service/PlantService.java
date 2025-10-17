package com.ecommerce.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Plant;
import com.ecommerce.repository.PlantRepo;

@Service
public class PlantService {
	
	@Autowired
	public PlantRepo plantRepo;
	
	public List<Plant> getPlantsWithOffer(){
		return plantRepo.findByOfferGreaterThan(0);
	}
	
	public List<Plant> getPlantsWithTop5Offers(){
		return plantRepo.findAll().stream()
				.filter(p -> p.getOffer()>0)
				.sorted(Comparator.comparingDouble(p -> Math.abs(p.getOffer()-100)))
				.limit(5)
				.collect(Collectors.toList());
	}
	

}
