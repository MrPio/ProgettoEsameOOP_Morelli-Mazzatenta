package com.univpm.po.NutritionStats.model.nutrient;

import java.io.Serializable;

public class Fiber extends NotNutrient implements Serializable {
	private float quantity;

	public Fiber(float quantity) {
		super(quantity);
	}
	
	public float getQuantity() {
		return quantity;
	}

}
