package com.ecommerce.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Plant;

public interface PlantRepo extends JpaRepository<Plant,UUID> {

}
