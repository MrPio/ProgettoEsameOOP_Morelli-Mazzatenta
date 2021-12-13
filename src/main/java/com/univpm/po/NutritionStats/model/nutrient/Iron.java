package com.univpm.po.NutritionStats.model.nutrient;

public class Iron extends MineralSalt {

	private float quantity;
	public Iron(float quantity) {
		super(quantity);
	}
	public float getQuantity() {
		return quantity;
	}
}
