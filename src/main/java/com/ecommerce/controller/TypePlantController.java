package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.TypePlant;
import com.ecommerce.repository.TypeRepo;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/")
public class TypePlantController {
	
//	@Autowired
//	private UserRepo userRepo;

//	@Autowired
//	private CartRepo cartRepo;
//	
//	@Autowired
//	private PlantRepo plantRepo;
//	
	@Autowired
	private TypeRepo typeRepo;
//	
//	@Autowired
//	private ImageRepo imageRepo;
	
	@PostMapping("/savetypeplant")
	public ResponseEntity<?> savetypeplant(@RequestBody TypePlant typePlant) {
		return ResponseEntity.ok().body(typeRepo.save(typePlant));
	}

}
