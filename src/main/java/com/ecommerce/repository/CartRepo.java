package com.ecommerce.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Cart;

public interface CartRepo extends JpaRepository<Cart,Integer> {
	
	public List<Cart> findByUserId(int id);
	
//	public Cart findById(int id); 

}
