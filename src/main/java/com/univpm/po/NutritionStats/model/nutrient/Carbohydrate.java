package com.univpm.po.NutritionStats.model.nutrient;

public class Carbohydrate extends MacroNutrient {
	
	final static int PERCENTAGE_DAILY_CARBOHYDRATES = 50;
	public final static int CALORIES_PER_CARBOHYDRATE = 4;
	private float quantityOnlySugar;
	
	public Carbohydrate(float quantity, float quantityOnlySugar) {
		super(quantity);
		this.quantityOnlySugar = quantityOnlySugar;
	}

	public float getQuantityOnlySugar() {
		return quantityOnlySugar;
	}

	@Override
	public float calculateCalories() {
		return quantity * CALORIES_PER_CARBOHYDRATE;
	}

}
