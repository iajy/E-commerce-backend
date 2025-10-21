package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Cart;
import com.ecommerce.repository.CartRepo;

@Service
public class CartService {

	@Autowired
	public CartRepo cartRepo;


}
