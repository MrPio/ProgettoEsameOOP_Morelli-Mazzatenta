package com.univpm.po.NutritionStats.model.nutrient;

public class WaterFromFood extends NotNutrient {
	private float quantity;
	
	public WaterFromFood(float quantity) {
		super(quantity);
	}
	
	public float getQuantity() {
		return quantity;
	}

}
