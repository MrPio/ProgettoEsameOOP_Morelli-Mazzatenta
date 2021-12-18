package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public abstract class Nutrient implements Serializable {
	private NutrientName name;
	private Measure measure;
	private float quantity;

	public Nutrient(NutrientName name, float quantity, Measure measure) {
		this.name = name;
		this.measure = measure;
		this.quantity = quantity;
	}

	public NutrientName getName() {
		return name;
	}

	public Measure getMeasure() {
		return measure;
	}

	public float getQuantity() {
		return quantity;
	}

}
