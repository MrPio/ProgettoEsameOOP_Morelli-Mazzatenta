package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

public class VitaminC extends Vitamin implements Serializable {

	public VitaminC(float quantity) {
		super(AllNutrientNonNutrient.VITAMIN_C,quantity);
	}

}
