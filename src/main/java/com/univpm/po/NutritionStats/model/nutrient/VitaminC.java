package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public class VitaminC extends Vitamin implements Serializable {

	public VitaminC(float quantity) {
		super(NutrientName.VITAMIN_C,quantity);
	}

}
