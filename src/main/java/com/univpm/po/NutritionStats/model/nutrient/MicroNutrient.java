package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;

import java.io.Serializable;

public abstract class MicroNutrient extends Nutrient implements Serializable {
	
	public MicroNutrient (float quantity) {
		super(quantity,Measure.GR);
	}
	
}
