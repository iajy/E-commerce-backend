package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByMail(String mail);

	boolean existsByMail(String mail);

}
