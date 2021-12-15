package com.univpm.po.NutritionStats.model.nutrient;

public class Protein extends MacroNutrient {

	final static int percentageDailyProtein = 20;
	
	public Protein(float quantity) {
		super(quantity);
	}

	@Override
	public float calculateCalories() {
		return quantity * 4;
	}

}
