package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;
/**
 * @author Davide
 * Represents the water from a food.
 */
public class WaterFromFood extends NotNutrient implements Serializable {
	/**
	 * Class constructor
	 * @param quantity
	 */
    public WaterFromFood(float quantity) {
        super(AllNutrientNonNutrient.WATER_FROM_FOOD, Measure.ML,quantity);
    }
}
