package com.univpm.po.NutritionStats.model.nutrient;

import java.io.Serializable;

public abstract class NotNutrient implements Serializable {
	
	private float quantity;
	
	public NotNutrient (float quantity) {
		this.quantity = quantity;
	}
	
	public float getQuantity() {
		return quantity;
	}
}
