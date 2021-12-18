package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public abstract class Vitamin extends MicroNutrient implements Serializable {

	public Vitamin(NutrientName name,float quantity) {
		super(name,quantity);
	}

}
