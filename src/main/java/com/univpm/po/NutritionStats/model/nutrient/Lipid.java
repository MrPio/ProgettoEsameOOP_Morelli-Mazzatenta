package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

public class Lipid extends MacroNutrient implements Serializable {

	private float quantityOnlySaturated;

	public Lipid(float quantity, float quantityOnlySaturated) {
		super(AllNutrientNonNutrient.LIPID, quantity);
		this.quantityOnlySaturated = quantityOnlySaturated;
	}

	public float getQuantityOnlySaturated() {
		return quantityOnlySaturated;
	}

	public void setQuantityOnlySaturated(float quantityOnlySaturated) {
		this.quantityOnlySaturated = quantityOnlySaturated;
	}

	@Override
	public float calculateCalories() {
		return getQuantity() * CALORIES_PER_GRAM.get(this.getClass());
	}
}
