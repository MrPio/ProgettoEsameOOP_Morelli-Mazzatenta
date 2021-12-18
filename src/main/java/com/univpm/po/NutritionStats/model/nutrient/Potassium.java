package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

public class Potassium extends MineralSalt implements Serializable {

	public Potassium(float quantity) {
		super(AllNutrientNonNutrient.POTASSIUM,quantity);
	}
}
