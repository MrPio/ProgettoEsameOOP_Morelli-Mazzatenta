package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represents a generic mineral salt which is a micronutrient and can be one
 * among calcium, potassium, sodium and iron.
 * 
 * @author Davide
 *
 */
public abstract class MineralSalt extends MicroNutrient implements Serializable {

	/**
	 * Class constructor that instantiates a mineral salt with quantity and "label".
	 * 
	 * @param name     of the mineral
	 * @param quantity of the mineral
	 */
	public MineralSalt(AllNutrientNonNutrient name, float quantity) {
		super(name, quantity);
	}
}
