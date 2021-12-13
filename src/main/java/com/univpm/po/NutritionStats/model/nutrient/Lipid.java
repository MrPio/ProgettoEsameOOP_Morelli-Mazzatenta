package com.univpm.po.NutritionStats.model.nutrient;

public class Lipid extends MacroNutrient {

	final static int percentageDailyLipid = 30;
	private float quantity;
	private float quantityOnlySatured;
	
	public Lipid(float quantity) {
		super(quantity);
	}
	
	public float getQuantity() {
		return quantity;
	}

	public float getQuantityOnlySatured() {
		return quantityOnlySatured;
	}

	@Override
	public float calculateCalories() {
		return quantity * 9;
	}

}
