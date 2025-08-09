package com.ecommerce.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.TypePlant;

public interface TypeRepo  extends JpaRepository <TypePlant,UUID>{

}
