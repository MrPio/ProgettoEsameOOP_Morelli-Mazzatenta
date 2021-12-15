package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;

import java.io.Serializable;

public abstract class Nutrient implements Serializable {
	
	protected Measure measure;
	protected float quantity;
	
	public Nutrient(float quantity,Measure measure) {
		this.quantity = quantity;
		this.measure = measure;
	}

	public float getQuantity() {
		return quantity;
	}

}
