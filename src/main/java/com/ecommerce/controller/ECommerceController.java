package com.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ecommerce.service.UserService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/")
public class ECommerceController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

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

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
		String token = request.get("token");
		String newPassword = request.get("newPassword");
		return ResponseEntity.ok().body(userService.resetPassword(token, newPassword));

	}

	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
		String mail = request.get("mail");
		userService.sendResetLink(mail);
		return ResponseEntity.ok("Reset link sent to your email");
	}

}
