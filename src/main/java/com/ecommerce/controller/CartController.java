package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Cart;
import com.ecommerce.repository.CartRepo;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/")
public class CartController {
	
	@Autowired
	public CartRepo cartRepo;
	
	@PostMapping("/addtocart")
	public ResponseEntity<?> addToCart(@RequestBody Cart cart){
		return ResponseEntity.ok().body(cartRepo.save(cart));
		
	}
	
	@GetMapping("/getcartbyuserid/{id}")
	public List<Cart> getCartByUserId(@PathVariable int id){
		
		return cartRepo.findByUserId(id);
		
	}

}
