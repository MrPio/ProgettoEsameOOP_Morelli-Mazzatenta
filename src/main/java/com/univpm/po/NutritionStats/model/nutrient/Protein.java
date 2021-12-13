package com.univpm.po.NutritionStats.model.nutrient;

public class Protein extends MacroNutrient {

	private float quantity;
	final static int percentageDailyProtein = 20;
	
	public Protein(float quantity) {
		super(quantity);
	}
	
	public float getQuantity() {
		return quantity;
	}

	@Override
	public float calculateCalories() {
		return quantity * 4;
	}

}
