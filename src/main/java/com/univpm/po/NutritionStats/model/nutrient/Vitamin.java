package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

public abstract class Vitamin extends MicroNutrient implements Serializable {

	public Vitamin(AllNutrientNonNutrient name,float quantity) {
		super(name,quantity);
	}

}
