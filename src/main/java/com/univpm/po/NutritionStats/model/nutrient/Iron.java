package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public class Iron extends MineralSalt implements Serializable {

	public Iron(float quantity) {
		super(NutrientName.IRON,quantity);
	}

}
