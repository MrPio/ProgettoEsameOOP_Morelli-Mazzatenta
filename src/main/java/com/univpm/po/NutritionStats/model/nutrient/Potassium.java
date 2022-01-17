package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a specified mineral salt, the potassium
 * 
 * @author Davide Mazzatenta
 *
 */
public class Potassium extends MineralSalt implements Serializable {

	/**
	 * Class constructor that instantiates potassium with quantity and "label".
	 * 
	 * @param quantity of potassium
	 */
	public Potassium(float quantity) {
		super(AllNutrientNonNutrient.POTASSIUM, quantity);
	}
}
