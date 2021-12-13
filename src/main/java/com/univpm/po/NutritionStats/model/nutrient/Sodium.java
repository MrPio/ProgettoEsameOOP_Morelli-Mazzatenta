package com.univpm.po.NutritionStats.model.nutrient;

public class Sodium extends MineralSalt {
	
	private float quantity;
	public Sodium(float quantity) {
		super(quantity);
	}
	public float getQuantity() {
		return quantity;
	}
}
