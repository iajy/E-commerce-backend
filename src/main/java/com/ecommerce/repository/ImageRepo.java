package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Image;

public interface ImageRepo extends JpaRepository <Image , Integer> {

}
