package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public class Potassium extends MineralSalt implements Serializable {

	public Potassium(float quantity) {
		super(NutrientName.POTASSIUM,quantity);
	}
}
