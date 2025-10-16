package com.ecommerce.controller;

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

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/")
public class PlantController {
	
//	@Autowired
//	private UserRepo userRepo;

//	@Autowired
//	private CartRepo cartRepo;
//	
	@Autowired
	private PlantRepo plantRepo;
//	
//	@Autowired
//	private TypeRepo typeRepo;
//	
//	@Autowired
//	private ImageRepo imageRepo;
	
	@GetMapping("/allplant")
	public ResponseEntity<?> allPlant(){
		return ResponseEntity.ok().body(plantRepo.findAll());
	}
	
	@PostMapping("/saveplant")
	public ResponseEntity<?> saveplant(@RequestBody Plant plant) {
		return ResponseEntity.ok().body(plantRepo.save(plant));
	}

}
