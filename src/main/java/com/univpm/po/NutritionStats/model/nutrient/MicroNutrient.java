package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;

public abstract class MicroNutrient extends Nutrient {
	
	public MicroNutrient (float quantity) {
		super(quantity,Measure.GR);
	}
	
}
