package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;

import java.io.Serializable;

public abstract class MacroNutrient extends Nutrient implements Serializable {
	
	public MacroNutrient (float quantity) {
		super(quantity,Measure.GR);
	}
	public abstract float calculateCalories();
}
