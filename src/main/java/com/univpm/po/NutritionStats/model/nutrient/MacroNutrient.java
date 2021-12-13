package com.univpm.po.NutritionStats.model.nutrient;

public abstract class MacroNutrient extends Nutrient {
	
	public MacroNutrient (float quantity) {
		super(quantity);
	}
	public abstract float calculateCalories();
}
