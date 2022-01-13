package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;


/**
 * Represent a specified vitamin, the vitaminC
 * @author Davide
 *
 */
public class VitaminA extends Vitamin implements Serializable {

	/**
	 * Class constructor
	 * @param quantity
	 */
	public VitaminA(float quantity) {
		super(AllNutrientNonNutrient.VITAMIN_A,quantity);
	}
}
