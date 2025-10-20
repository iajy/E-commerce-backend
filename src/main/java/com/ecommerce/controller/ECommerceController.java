package com.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.config.JwtUtil;
import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepo;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/")
public class ECommerceController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtUtil jwtUtil;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostMapping("/saveuser")
	public Map<String, Object> registerUser(@RequestBody User user) {

		Map<String, Object> res = new HashMap<>();

		if (userRepo.findByMail(user.getMail()).isPresent()) {
			res.put("message", "Email already exits");
			return res;
		}
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		String token = jwtUtil.generateToken(user.getMail());
		res.put("token", token);
		res.put("user", user);
		return res;
	}

	@PostMapping("/loginuser")
	public Map<String, Object> loginUser(@RequestBody User loginUser) {

		Map<String, Object> res = new HashMap<>();

		Optional<User> userOpt = userRepo.findByMail(loginUser.getMail());

		if (userOpt.isEmpty()) {
			res.put("message", "invalid Email");
			return res;
		}

		User user = userOpt.get();

		if (!encoder.matches(loginUser.getPassword(), user.getPassword())) {
			res.put("message", "Invalid password");
			return res;
		}

		String token = jwtUtil.generateToken(user.getMail());

		res.put("token", token);
		res.put("user", user);
		return res;

	}

}
