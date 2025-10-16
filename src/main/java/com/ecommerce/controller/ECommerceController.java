package com.ecommerce.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepo;
import com.ecommerce.repository.ImageRepo;
import com.ecommerce.repository.PlantRepo;
import com.ecommerce.repository.TypeRepo;
import com.ecommerce.repository.UserRepo;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/")
public class ECommerceController {

	@Autowired
	private UserRepo userRepo;

//	@Autowired
//	private CartRepo cartRepo;
//	
//	@Autowired
//	private PlantRepo plantRepo;
//	
//	@Autowired
//	private TypeRepo typeRepo;
//	
//	@Autowired
//	private ImageRepo imageRepo;
	
	@PostMapping("/saveuser")
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		if (userRepo.existsByMail(user.getMail())) {
			System.out.println(user.getMail());
			return ResponseEntity.badRequest().body("Email already exists. Please use another one.");
		}
		return ResponseEntity.ok().body(userRepo.save(user));
	}

	@PostMapping("/loginuser")
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {

		String email = loginData.get("email");
		String password = loginData.get("password");

		User user = userRepo.findByMail(email).orElse(null);

		if (user != null && password.equals(user.getPassword())) {
			return ResponseEntity.ok("Login successful");
		} else {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
		}

	}

}
