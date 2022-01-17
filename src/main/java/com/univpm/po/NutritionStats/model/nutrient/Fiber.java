package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represents the fibers of a food.
 * 
 * @author Davide Mazzatenta
 */
public class Fiber extends NotNutrient implements Serializable {
	/**
	 * Class constructor that instantiates a fiber with quantity, "label" and unity of
	 * measure that is grams
	 * 
	 * @param quantity of fiber
	 */
	public Fiber(float quantity) {
		super(AllNutrientNonNutrient.FIBER, Measure.GR, quantity);
	}
}
