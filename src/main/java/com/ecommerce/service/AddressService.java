package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.entity.Address;
import com.ecommerce.entity.User;
import com.ecommerce.repository.AddressRepo;
import com.ecommerce.repository.UserRepo;

@Service
public class AddressService {

	@Autowired
	public AddressRepo addressRepo;

	@Autowired
	public UserRepo userRepo;

	public ResponseEntity<String> saveAddress(int userId, Address updatedAddress) {

		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));

		int addressCount = addressRepo.countByUserId(userId);

		if (addressCount >= 3) {
			return ResponseEntity.badRequest().body("Delete one Address to add another");
		}

		Address address = new Address();
		address.setName(updatedAddress.getName());
		address.setDistrict(updatedAddress.getDistrict());
		address.setAddress(updatedAddress.getAddress());
		address.setState(updatedAddress.getState());
		address.setPincode(updatedAddress.getPincode());
		address.setPhnNumber(updatedAddress.getPhnNumber());
		address.setUser(user);

		addressRepo.save(address);

		return ResponseEntity.ok("Address added");

	}

	public ResponseEntity<String> deleteAddress(int id) {
		addressRepo.deleteById(id);
		return ResponseEntity.ok().body("Address Deleted");
	}

	public List<Address> getAddress(int userId) {
		if (!userRepo.existsById(userId)) {
			throw new RuntimeException("User Not Found");
		}
		return addressRepo.findByUserId(userId);
	}

	public ResponseEntity<String> editAddres(int addressId, Address address) {
		if (!addressRepo.existsById(addressId)) {
			throw new RuntimeException("Address ID Not Found");
		}

		Address addr = addressRepo.findById(addressId).orElseThrow(() -> new RuntimeException("Address ID Not Found"));

		addr.setName(address.getName());
		addr.setDistrict(address.getDistrict());
		addr.setAddress(address.getAddress());
		addr.setState(address.getState());
		addr.setPincode(address.getPincode());
		addr.setPhnNumber(address.getPhnNumber());

		addressRepo.save(addr);

		return ResponseEntity.ok("Address updated successfully");

	}

}
