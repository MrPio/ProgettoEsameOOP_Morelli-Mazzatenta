package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a specified mineral salt, the calcium
 * 
 * @author Davide Mazzatenta
 *
 */
public class Calcium extends MineralSalt implements Serializable {

	/**
	 * Class constructor that instantiates calcium with his quantity and a "label"
	 * 
	 * @param quantity of calcium
	 */
	public Calcium(float quantity) {
		super(AllNutrientNonNutrient.CALCIUM, quantity);
	}
}
