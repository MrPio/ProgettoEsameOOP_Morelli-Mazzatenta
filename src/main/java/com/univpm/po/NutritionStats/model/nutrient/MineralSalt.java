package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represents a mineral salt which is a micronutrient
 * @author Davide
 *
 */
public abstract class MineralSalt extends MicroNutrient implements Serializable {
	
	/**
	 * Class constructor 
	 * @param name
	 * @param quantity
	 */
	public MineralSalt(AllNutrientNonNutrient name, float quantity) {
		super(name,quantity);
	}
}
