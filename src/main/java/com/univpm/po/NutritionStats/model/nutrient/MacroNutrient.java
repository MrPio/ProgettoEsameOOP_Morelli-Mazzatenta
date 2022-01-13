package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Water;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a generic macronutrient 
 * @author Davide
 *
 */
public abstract class MacroNutrient extends Nutrient implements Serializable {
	public final static Map<Class<?>, Integer> CALORIES_PER_GRAM = new HashMap<>() {
		{
			put(Carbohydrate.class, 4);
			put(Lipid.class, 9);
			put(Protein.class, 4);
		}
	};
	
	/**
	 * Class constructor
	 * @param name
	 * @param quantity
	 */
	public MacroNutrient(AllNutrientNonNutrient name, float quantity) {
		super(name, quantity, Measure.GR);
	}

	/**
	 * Abstract method that calculate the calories dependind on which macronutrient is it 
	 * @return
	 */
	public abstract float calculateCalories();

}
