package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.entity.Address;
import com.ecommerce.service.AddressService;

@Controller
public class AddressController {

	@Autowired
	public AddressService addressService;

	@PostMapping("saveaddress/{id}")
	public ResponseEntity<String> saveAddress(@RequestBody Address updatedAddress, @PathVariable int id) {
		return addressService.saveAddress(id, updatedAddress);
	}

	@DeleteMapping("deleteaddress/{id}")
	public ResponseEntity<String> deleteAddress(@PathVariable int id) {
		return addressService.deleteAddress(id);

	}
	
	@GetMapping("/address/{id}")
	public ResponseEntity<List<Address>> getAddress(@PathVariable int id){
		List<Address> addreses = addressService.getAddress(id);
		return ResponseEntity.ok().body(addreses);
	}
	
	@PutMapping("/editaddress/{id}")
	public ResponseEntity<String> editAddress(@PathVariable int id,@RequestBody Address address){
		
		return addressService.editAddres(id, address);
		
	}

}
