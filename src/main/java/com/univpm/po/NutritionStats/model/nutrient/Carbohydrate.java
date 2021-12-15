package com.univpm.po.NutritionStats.model.nutrient;

import java.io.Serializable;

public class Carbohydrate extends MacroNutrient implements Serializable {
	
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
