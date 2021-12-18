package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

public class Calcium extends MineralSalt implements Serializable {

	public Calcium(float quantity) {
		super(AllNutrientNonNutrient.CALCIUM,quantity);
	}
}
