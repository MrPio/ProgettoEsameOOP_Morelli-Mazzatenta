package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a carbohydrate which is a macronutrient 
 * @author Davide
 *
 */
public class Carbohydrate extends MacroNutrient implements Serializable {

	private float quantityOnlySugar;

	/**
	 * Class constructor
	 * @param quantity
	 * @param quantityOnlySugar
	 */
	public Carbohydrate(float quantity, float quantityOnlySugar) {
		super(AllNutrientNonNutrient.CARBOHYDRATE, quantity);
		this.quantityOnlySugar = quantityOnlySugar;
	}

	/**
	 * @return the quantity of only sugar carbohydrates 
	 */
	public float getQuantityOnlySugar() {
		return quantityOnlySugar;
	}

	/**
     * set the only sugar quantity passed by the param
	 * @param quantityOnlySugar
	 */
	public void setQuantityOnlySugar(float quantityOnlySugar) {
		this.quantityOnlySugar = quantityOnlySugar;
	}

	/**
	 * Calculate the calories of a carbohydrate
	 */
	@Override
	public float calculateCalories() {
		return getQuantity() * CALORIES_PER_GRAM.get(this.getClass());
	}

}
