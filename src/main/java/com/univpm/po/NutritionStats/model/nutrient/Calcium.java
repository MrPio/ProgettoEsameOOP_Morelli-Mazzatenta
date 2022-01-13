package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a specified mineral salt, the calcium
 * @author Davide
 *
 */
public class Calcium extends MineralSalt implements Serializable {

	/**
	 * Class constructor
	 * @param quantity
	 */
	public Calcium(float quantity) {
		super(AllNutrientNonNutrient.CALCIUM,quantity);
	}
}
