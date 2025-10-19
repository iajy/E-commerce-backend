package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {
 
	public int countByUserId(int userId);
	
	public List<Address> findByUserId(int useId);
}
