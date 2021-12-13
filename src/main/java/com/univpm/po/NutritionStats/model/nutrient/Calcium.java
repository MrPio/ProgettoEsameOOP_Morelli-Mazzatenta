package com.univpm.po.NutritionStats.model.nutrient;

public class Calcium extends MineralSalt {

	private float quantity;
	public Calcium(float quantity) {
		super(quantity);
	}
	public float getQuantity() {
		return quantity;
	}
}
