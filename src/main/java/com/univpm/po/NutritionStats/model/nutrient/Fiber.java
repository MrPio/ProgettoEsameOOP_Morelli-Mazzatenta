package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

public class Fiber extends NotNutrient implements Serializable {
	public Fiber(float quantity) {
		super(AllNutrientNonNutrient.FIBER, Measure.GR,quantity);
	}
}
