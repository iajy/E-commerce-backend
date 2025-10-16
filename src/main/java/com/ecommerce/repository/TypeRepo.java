package com.ecommerce.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.TypePlant;

public interface TypeRepo  extends JpaRepository <TypePlant,Integer>{

}
