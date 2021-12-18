package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

public class VitaminA extends Vitamin implements Serializable {

	public VitaminA(float quantity) {
		super(AllNutrientNonNutrient.VITAMIN_A,quantity);
	}
}
