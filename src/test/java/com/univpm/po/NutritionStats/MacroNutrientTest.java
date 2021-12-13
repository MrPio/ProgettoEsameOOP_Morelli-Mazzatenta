package com.univpm.po.NutritionStats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.univpm.po.NutritionStats.model.nutrient.Lipid;
import com.univpm.po.NutritionStats.model.nutrient.MacroNutrient;
import com.univpm.po.NutritionStats.model.nutrient.NotNutrient;
import com.univpm.po.NutritionStats.model.nutrient.Nutrient;
import com.univpm.po.NutritionStats.model.nutrient.Protein;

class MacroNutrientTest {

	@Test
	void testNutrient1() {
		assertTrue (new Protein(10) instanceof Nutrient);
	}
	
}
