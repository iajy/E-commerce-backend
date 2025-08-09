package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.repository.CartRepo;
import com.ecommerce.repository.PlantRepo;
import com.ecommerce.repository.TypeRepo;
import com.ecommerce.repository.UserRepo;

@RestController
@RequestMapping("/")

//@CrossOrigin

public class ECommerceController {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private PlantRepo plantRepo;
	
	@Autowired
	private TypeRepo typeRepo;
	
	
	public ResponseEntity<?> registerUser(){
		return ResponseEntity.ok("User Registered");
	}
	
}
