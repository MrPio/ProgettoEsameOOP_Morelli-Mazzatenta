package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represents the fibers of a food.
 * 
 * @author Davide
 */
public class Fiber extends NotNutrient implements Serializable {
	/**
	 * Class constructor
	 * 
	 * @param quantity of fiber
	 */
	public Fiber(float quantity) {
		super(AllNutrientNonNutrient.FIBER, Measure.GR, quantity);
	}
}
