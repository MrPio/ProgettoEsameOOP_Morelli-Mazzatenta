package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public class VitaminA extends Vitamin implements Serializable {

	public VitaminA(float quantity) {
		super(NutrientName.VITAMIN_A,quantity);
	}
}
