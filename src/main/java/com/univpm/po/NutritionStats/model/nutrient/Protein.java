package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public class Protein extends MacroNutrient implements Serializable {

	public Protein(float quantity) {
		super(NutrientName.PROTEIN, quantity);
	}

	@Override
	public float calculateCalories() {
		return getQuantity() * CALORIES_PER_GRAM.get(this.getClass());
	}
}
