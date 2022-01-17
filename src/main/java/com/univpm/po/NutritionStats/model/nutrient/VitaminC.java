package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a specified vitamin, the vitamin C.
 * 
 * @author Davide Mazzatenta
 *
 */
public class VitaminC extends Vitamin implements Serializable {

	/**
	 * Class constructor that instantiates vitamin C with quantity and "label".
	 * 
	 * @param quantity of vitamin C
	 */
	public VitaminC(float quantity) {
		super(AllNutrientNonNutrient.VITAMIN_C, quantity);
	}

}
