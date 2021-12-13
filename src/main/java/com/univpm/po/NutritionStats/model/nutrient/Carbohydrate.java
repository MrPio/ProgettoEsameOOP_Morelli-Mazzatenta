package com.univpm.po.NutritionStats.model.nutrient;

public class Carbohydrate extends MacroNutrient {
	
	final static int percentageDailyCarbohydrate = 50;
	private float quantity;
	private float quantityOnlySugar;
	
	public Carbohydrate(float quantity, float quantityOnlySugar) {
		super(quantity);
		this.quantityOnlySugar = quantityOnlySugar;
	}
	
	public float getQuantity() {
		return quantity;
	}


	public float getQuantityOnlySugar() {
		return quantityOnlySugar;
	}

	@Override
	public float calculateCalories() {
		return quantity * 4;
	}

}
