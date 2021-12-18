package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public abstract class MacroNutrient extends Nutrient implements Serializable {
	
	public MacroNutrient (NutrientName name,float quantity) {
		super(name,quantity,Measure.GR);
	}
	public abstract float calculateCalories();
}
