package com.univpm.po.NutritionStats.model.nutrient;

public class Fiber extends NotNutrient {
	private float quantity;

	public Fiber(float quantity) {
		super(quantity);
	}
	
	public float getQuantity() {
		return quantity;
	}

}
