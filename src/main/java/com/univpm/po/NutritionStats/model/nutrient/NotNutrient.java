package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represents a generic not nutrient which is one between fiber or 
 * water from food
 * 
 * @author Davide
 */
public abstract class NotNutrient implements Serializable {
	private AllNutrientNonNutrient name;
	private Measure measure;
	private float quantity;

	/**
	 * Class constructor
	 * 
	 * @param name     of a not nutrient
	 * @param measure  the unity of measure of a not nutrient
	 * @param quantity of a not nutrient
	 */
	public NotNutrient(AllNutrientNonNutrient name, Measure measure, float quantity) {
		this.name = name;
		this.measure = measure;
		this.quantity = quantity;
	}

	/**
	 * @return name of a not nutrient
	 */
	public AllNutrientNonNutrient getName() {
		return name;
	}

	/**
	 * @return quantity of a not nutrient
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
