package com.ecommerce.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.config.JwtUtil;
import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtUtil jwtUtil;


	public ResponseEntity<?> resetPassword(String token, String newPassword) {
		Optional<User> optionalUser = userRepo.findByResetToken(token);

		if (optionalUser.isEmpty()) {
			throw new RuntimeException("Invalid Token");
		}

		User user = optionalUser.get();
		user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
		user.setResetToken(null);
		userRepo.save(user);
		return ResponseEntity.ok().body(user);		
	}

	public void sendResetLink(String mail) {
		Optional<User> optionalUser = userRepo.findByMail(mail);
		if (optionalUser.isEmpty()) {
			throw new RuntimeException("Email not found");
		}

		User user = optionalUser.get();
		String token = jwtUtil.generateToken(user.getMail());
		user.setResetToken(token);
		userRepo.save(user);

		String resetLink = "http://localhost:5173/reset-password/" + token;
		String subject = "Password Reset Request";
		String body = "Click the link to reset your password: " + resetLink;

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

}
