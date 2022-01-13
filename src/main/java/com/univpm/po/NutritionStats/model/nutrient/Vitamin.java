package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represents a vitamin which is a micronutrient 
 * @author Davide
 *
 */
public abstract class Vitamin extends MicroNutrient implements Serializable {

	/**
	 * Class constructor
	 * @param name
	 * @param quantity
	 */
	public Vitamin(AllNutrientNonNutrient name,float quantity) {
		super(name,quantity);
	}

}
