package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public class Calcium extends MineralSalt implements Serializable {

	public Calcium(float quantity) {
		super(NutrientName.CALCIUM,quantity);
	}
}
