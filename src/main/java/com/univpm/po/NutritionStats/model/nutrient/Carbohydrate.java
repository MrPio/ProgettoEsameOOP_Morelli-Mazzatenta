package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public class Carbohydrate extends MacroNutrient implements Serializable {

	final static int PERCENTAGE_DAILY_CARBOHYDRATES = 50;
	public final static int CALORIES_PER_CARBOHYDRATE = 4;
	private float quantityOnlySugar;

	public Carbohydrate(float quantity, float quantityOnlySugar) {
		super(NutrientName.CARBOHYDRATE, quantity, CALORIES_PER_CARBOHYDRATE);
		this.quantityOnlySugar = quantityOnlySugar;
	}

	public float getQuantityOnlySugar() {
		return quantityOnlySugar;
	}

	@Override
	public float calculateCalories() {
		return getQuantity() * CALORIES_PER_CARBOHYDRATE;
	}

}
