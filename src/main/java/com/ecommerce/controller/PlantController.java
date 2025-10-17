package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Plant;
import com.ecommerce.repository.PlantRepo;
import com.ecommerce.service.PlantService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/")
public class PlantController {
		
	@Autowired
    private PlantService plantService;
	
	@Autowired
	private PlantRepo plantRepo;

	@GetMapping("/allplant")
	public ResponseEntity<?> allPlant(){
		return ResponseEntity.ok().body(plantRepo.findAll());
	}
	
	@PostMapping("/saveplant")
	public ResponseEntity<?> saveplant(@RequestBody Plant plant) {
		return ResponseEntity.ok().body(plantRepo.save(plant));
	}
	
	@GetMapping("/plantbyoffer")
	public List<Plant> plantByOffer(){
		return plantService.getPlantsWithOffer();
		
	}
	
	@GetMapping("/plantbytopoffer")
	public List<Plant> plantByTopOffer(){
		return plantService.getPlantsWithTop5Offers();
		
	}

}
