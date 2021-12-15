package com.univpm.po.NutritionStats.model.nutrient;

import java.io.Serializable;

public abstract class Vitamin extends MicroNutrient implements Serializable {

	public Vitamin(float quantity) {
		super(quantity);
	}

}
