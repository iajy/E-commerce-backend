package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Image;
import com.ecommerce.repository.CartRepo;
import com.ecommerce.repository.ImageRepo;
import com.ecommerce.repository.PlantRepo;
import com.ecommerce.repository.TypeRepo;
import com.ecommerce.repository.UserRepo;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/")
public class ImageController {

//	@Autowired
//	private UserRepo userRepo;
//
//	@Autowired
//	private CartRepo cartRepo;
//	
//	@Autowired
//	private PlantRepo plantRepo;
//	
//	@Autowired
//	private TypeRepo typeRepo;
	
	@Autowired
	private ImageRepo imageRepo;
	
	
	@PostMapping("/saveimage")
	public ResponseEntity<?> saveImage(@RequestBody Image image){
		return ResponseEntity.ok().body(imageRepo.save(image));
	}
}
