package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a protein which is a macronutrient
 * 
 * @author Davide
 *
 */
public class Protein extends MacroNutrient implements Serializable {

	/**
	 * Class constructor
	 * 
	 * @param quantity of protein
	 */
	public Protein(float quantity) {
		super(AllNutrientNonNutrient.PROTEIN, quantity);
	}

	/**
	 * Calculate the calories of a protein
	 */
	@Override
	public float calculateCalories() {
		return getQuantity() * CALORIES_PER_GRAM.get(this.getClass());
	}
}
