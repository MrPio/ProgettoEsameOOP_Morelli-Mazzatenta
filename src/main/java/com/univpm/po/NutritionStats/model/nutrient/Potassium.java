package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a specified mineral salt, the potassium
 * @author Davide
 *
 */
public class Potassium extends MineralSalt implements Serializable {

	/**
	 * Class constructor
	 * @param quantity
	 */
	public Potassium(float quantity) {
		super(AllNutrientNonNutrient.POTASSIUM,quantity);
	}
}
