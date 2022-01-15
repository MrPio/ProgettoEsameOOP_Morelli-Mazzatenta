package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represents a generic nutrient which is one among macronutrients and
 * micronutrients.
 * 
 * @author Davide
 * 
 */
public abstract class Nutrient implements Serializable {
	private AllNutrientNonNutrient name;
	private Measure measure;
	private float quantity;

	/**
	 * Class constructor
	 * 
	 * @param name     of a nutrient
	 * @param quantity of a nutrient
	 * @param measure  the unity of measure of a nutrient
	 */
	public Nutrient(AllNutrientNonNutrient name, float quantity, Measure measure) {
		this.name = name;
		this.measure = measure;
		this.quantity = quantity;
	}

	/**
	 * @return the name of a nutrient
	 */
	public AllNutrientNonNutrient getName() {
		return name;
	}

	/**
	 * @return the unity of measure of a nutrient
	 */
	public Measure getMeasure() {
		return measure;
	}

	/**
	 * @return quantity of a nutrient
	 */
	public float getQuantity() {
		return quantity;
	}

	/**
	 * set the quantity passed by the parameter
	 * 
	 * @param quantity to set 
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
}
