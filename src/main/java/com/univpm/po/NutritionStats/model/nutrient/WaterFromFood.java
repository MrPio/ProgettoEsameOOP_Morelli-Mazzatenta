package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represents the water coming from a food.
 * 
 * @author Davide 
 */
public class WaterFromFood extends NotNutrient implements Serializable {
	/**
	 * Class constructor
	 * 
	 * @param quantity of water
	 */
	public WaterFromFood(float quantity) {
		super(AllNutrientNonNutrient.WATER_FROM_FOOD, Measure.ML, quantity);
	}
}
