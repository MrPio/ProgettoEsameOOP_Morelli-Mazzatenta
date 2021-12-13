package com.univpm.po.NutritionStats.model.nutrient;

public abstract class NotNutrient {
	
	private float quantity;
	
	public NotNutrient (float quantity) {
		this.quantity = quantity;
	}
	
	public float getQuantity() {
		return quantity;
	}
}
