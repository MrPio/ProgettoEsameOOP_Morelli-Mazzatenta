package com.univpm.po.NutritionStats.model.nutrient;

public abstract class Nutrient {
	
	private float quantity;
	
	public Nutrient (float quantity) {
		this.quantity = quantity;
	}
	
	public float getQuantity() {
		return quantity;
	}
}
