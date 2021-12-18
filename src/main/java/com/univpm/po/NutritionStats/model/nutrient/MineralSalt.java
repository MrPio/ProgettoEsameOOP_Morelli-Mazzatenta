package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public abstract class MineralSalt extends MicroNutrient implements Serializable {
	
	public MineralSalt(NutrientName name, float quantity) {
		super(name,quantity);
	}
}
