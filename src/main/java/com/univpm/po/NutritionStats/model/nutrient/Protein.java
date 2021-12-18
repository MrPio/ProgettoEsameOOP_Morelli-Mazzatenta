package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public class Protein extends MacroNutrient implements Serializable {

	final static int PERCENTAGE_DAILY_PROTEIN = 20;
	public final static int CALORIES_PER_PROTEIN = 4;
	
	public Protein(float quantity) {
		super(NutrientName.PROTEIN,quantity);
	}

	@Override
	public float calculateCalories() {
		return getQuantity() * CALORIES_PER_PROTEIN;
	}

}
