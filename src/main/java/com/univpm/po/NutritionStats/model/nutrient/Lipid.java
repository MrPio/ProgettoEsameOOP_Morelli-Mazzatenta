package com.univpm.po.NutritionStats.model.nutrient;

public class Lipid extends MacroNutrient {

	final static int percentageDailyLipid = 30;
	private float quantityOnlySatured;

	public Lipid(float quantity, float quantityOnlySatured) {
		super(quantity);
		this.quantityOnlySatured = quantityOnlySatured;
	}

	public float getQuantityOnlySatured() {
		return quantityOnlySatured;
	}

	@Override
	public float calculateCalories() {
		return quantity * 9;
	}

}
