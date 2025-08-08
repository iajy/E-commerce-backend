package com.ecommerce.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="plant")
public class Plant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private UUID id;
	
	private String plantName;
	
	private double prize;
	
	private TypePlant typePlant;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public double getPrize() {
		return prize;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}

	public TypePlant getTypePlant() {
		return typePlant;
	}

	public void setTypePlant(TypePlant typePlant) {
		this.typePlant = typePlant;
	}
	
	
	
	

}
