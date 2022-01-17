package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Water;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a generic macronutrient which is the part of nutrients with
 * calories. It can be a carbohydrate, a lipid or a protein.
 * 
 * @author Davide Mazzatenta
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
	 * Class constructor that instantiates a macronutrient with quantity and "label"
	 * and unity of measure which is grams.
	 * 
	 * @param name     of a macronutrient which is one among carbohydrate, lipid or
	 *                 protein
	 * @param quantity of a macronutrient expressed in grams
	 */
	public MacroNutrient(AllNutrientNonNutrient name, float quantity) {
		super(name, quantity, Measure.GR);
	}

	/**
	 * Abstract method that calculate the calories dependindg on which macronutrient
	 * is it
	 * 
	 * @return total calories of a macronutrient
	 */
	public abstract float calculateCalories();

}
