package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

public abstract class MineralSalt extends MicroNutrient implements Serializable {
	
	public MineralSalt(AllNutrientNonNutrient name, float quantity) {
		super(name,quantity);
	}
}
