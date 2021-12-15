package com.univpm.po.NutritionStats.model.nutrient;

import java.io.Serializable;

public abstract class MineralSalt extends MicroNutrient implements Serializable {
	
	public MineralSalt(float quantity) {
		super(quantity);
	}
}
