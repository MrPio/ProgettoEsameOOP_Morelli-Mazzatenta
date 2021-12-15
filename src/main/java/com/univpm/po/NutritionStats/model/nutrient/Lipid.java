package com.univpm.po.NutritionStats.model.nutrient;

import java.io.Serializable;

public class Lipid extends MacroNutrient implements Serializable {

	final static int PERCENTAGE_DAILY_LIPID = 30;
	public final static int CALORIES_PER_LIPID = 9;
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
		return quantity * CALORIES_PER_LIPID;
	}

}
