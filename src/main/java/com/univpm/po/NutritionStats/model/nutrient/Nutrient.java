package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;

public abstract class Nutrient {
	
	private Measure measure;
	private float quantity;
	
	public Nutrient(float quantity,Measure measure) {
		this.quantity = quantity;
		this.measure = measure;
	}



	public float getQuantity() {
		return quantity;
	}
}
