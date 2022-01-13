package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a lipid which is a macronutrient 
 * @author Davide
 *
 */
public class Lipid extends MacroNutrient implements Serializable {

	private float quantityOnlySaturated;

	/**
	 * Class constructor
	 * @param quantity
	 * @param quantityOnlySaturated
	 */
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

	/**
	 * Calculate the calories of a lipid
	 */
	@Override
	public float calculateCalories() {
		return getQuantity() * CALORIES_PER_GRAM.get(this.getClass());
	}
}
