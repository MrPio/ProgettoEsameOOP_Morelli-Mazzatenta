package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a carbohydrate which is a macronutrient
 * 
 * @author Davide
 *
 */
public class Carbohydrate extends MacroNutrient implements Serializable {

	private float quantityOnlySugar;

	/**
	 * Class constructor that instantiates a carbohydrate with quantity, only sugar
	 * quantity and a "label".
	 * 
	 * @param quantity          of carbohydrates
	 * @param quantityOnlySugar of these carbohydrates
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
	 * set the only sugar quantity passed by the parameter
	 * 
	 * @param quantityOnlySugar to set
	 */
	public void setQuantityOnlySugar(float quantityOnlySugar) {
		this.quantityOnlySugar = quantityOnlySugar;
	}

	/**
	 * Calculate the calories of a carbohydrate through the quantity and calories
	 * per gram
	 */
	@Override
	public float calculateCalories() {
		return getQuantity() * CALORIES_PER_GRAM.get(this.getClass());
	}

}
