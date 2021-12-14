package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;

public abstract class MacroNutrient extends Nutrient {
	
	public MacroNutrient (float quantity) {
		super(quantity,Measure.G);
	}
	public abstract float calculateCalories();
}
