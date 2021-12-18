package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public abstract class MacroNutrient extends Nutrient implements Serializable {

	private int caloriesPerNutrient;

	public MacroNutrient(NutrientName name, float quantity, int caloriesPerNutrient) {
		super(name, quantity, Measure.GR);
		this.caloriesPerNutrient = caloriesPerNutrient;
	}

	public int getCaloriesPerNutrient() {
		return caloriesPerNutrient;
	}

	public abstract float calculateCalories();
}
