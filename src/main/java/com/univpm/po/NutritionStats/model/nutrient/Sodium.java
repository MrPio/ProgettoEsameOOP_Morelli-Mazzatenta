package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

public class Sodium extends MineralSalt implements Serializable {
	
	public Sodium(float quantity) {
		super(AllNutrientNonNutrient.SODIUM,quantity);
	}
}
