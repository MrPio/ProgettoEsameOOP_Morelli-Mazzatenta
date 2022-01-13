package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a specified mineral salt, the sodium
 * @author Davide
 *
 */
public class Sodium extends MineralSalt implements Serializable {
	
	/**
	 * Class constructor
	 * @param quantity
	 */
	public Sodium(float quantity) {
		super(AllNutrientNonNutrient.SODIUM,quantity);
	}
}
