package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public class Lipid extends MacroNutrient implements Serializable {

	final static int PERCENTAGE_DAILY_LIPID = 30;
	public final static int CALORIES_PER_LIPID = 9;
	private float quantityOnlySatured;

	public Lipid(float quantity, float quantityOnlySatured) {
		super(NutrientName.LIPID, quantity, CALORIES_PER_LIPID);
		this.quantityOnlySatured = quantityOnlySatured;
	}

	public float getQuantityOnlySatured() {
		return quantityOnlySatured;
	}

	@Override
	public float calculateCalories() {
		return getQuantity() * CALORIES_PER_LIPID;
	}

}
