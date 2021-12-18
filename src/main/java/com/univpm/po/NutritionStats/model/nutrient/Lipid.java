package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public class Lipid extends MacroNutrient implements Serializable {

	private float quantityOnlySatured;

	public Lipid(float quantity, float quantityOnlySatured) {
		super(NutrientName.LIPID, quantity);
		this.quantityOnlySatured = quantityOnlySatured;
	}

	public float getQuantityOnlySatured() {
		return quantityOnlySatured;
	}

	@Override
	public float calculateCalories() {
		return getQuantity() * CALORIES_PER_GRAM.get(this.getClass());
	}
}
