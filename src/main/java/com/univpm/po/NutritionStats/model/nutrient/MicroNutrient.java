package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represents a generic micronutrient
 * @author Davide
 *
 */
public abstract class MicroNutrient extends Nutrient implements Serializable {

	/**
	 * Class constructor
	 * @param name
	 * @param quantity
	 */
    public MicroNutrient(AllNutrientNonNutrient name, float quantity) {
        super(name, quantity, Measure.GR);
    }
}
