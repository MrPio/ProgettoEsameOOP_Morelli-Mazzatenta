package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a lipid which is a macronutrient
 * 
 * @author Davide Mazzatenta
 *
 */
public class Lipid extends MacroNutrient implements Serializable {

	private float quantityOnlySaturated;

	/**
	 * Class constructor that instantiates a lipid with quantity, only satured
	 * quantity and "label"
	 * 
	 * @param quantity              of lipids
	 * @param quantityOnlySaturated of these lipids
	 */
	public Lipid(float quantity, float quantityOnlySaturated) {
		super(AllNutrientNonNutrient.LIPID, quantity);
		this.quantityOnlySaturated = quantityOnlySaturated;
	}

	/**
	 * @return the quantity of only satured lipids
	 */
	public float getQuantityOnlySaturated() {
		return quantityOnlySaturated;
	}

	/**
	 * set the only satured quantity passed by the parameter
	 * 
	 * @param quantityOnlySaturated to set
	 */
	public void setQuantityOnlySaturated(float quantityOnlySaturated) {
		this.quantityOnlySaturated = quantityOnlySaturated;
	}

	/**
	 * Calculate the calories of a lipid through the quantity and calories
	 * per gram
	 */
	@Override
	public float calculateCalories() {
		return getQuantity() * CALORIES_PER_GRAM.get(this.getClass());
	}
}
