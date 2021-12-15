package com.univpm.po.NutritionStats.model.nutrient;

public class Protein extends MacroNutrient {

	final static int PERCENTAGE_DAILY_PROTEIN = 20;
	public final static int CALORIES_PER_PROTEIN = 4;
	
	public Protein(float quantity) {
		super(quantity);
	}

	@Override
	public float calculateCalories() {
		return quantity * CALORIES_PER_PROTEIN;
	}

}
