package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Cart;
import com.ecommerce.repository.CartRepo;
import com.ecommerce.service.CartService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/")
public class CartController {

	@Autowired
	public CartRepo cartRepo;

	@Autowired
	public CartService cartService;

	@PostMapping("/addtocart")
	public ResponseEntity<?> addToCart(@RequestBody Cart cart) {
		cart.setCount(1);
		return ResponseEntity.ok().body(cartRepo.save(cart));

	}

	@GetMapping("/getcartbyuserid/{id}")
	public List<Cart> getCartByUserId(@PathVariable int id) {

		return cartRepo.findByUserId(id);

	}

	@PostMapping("/itemcountadd/{id}/{count}")
	public ResponseEntity<?> itemCountAdd(@PathVariable int id, @PathVariable int count) {
		Cart cart = cartRepo.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
		if (cart.getCount() >= 1) {
			if (count > 0) {
				cart.setCount(cart.getCount() + 1);
			} else if (count < 0) {
				cart.setCount(cart.getCount() - 1);
				
			}
		}
		return ResponseEntity.ok().body(cartRepo.save(cart));

	}
	
	@DeleteMapping("/deletecart/{id}")
	public ResponseEntity<?> deleteCart(@PathVariable int id){
		cartRepo.deleteById(id);
		return ResponseEntity.ok().body(cartRepo.findAll());
	}

}
