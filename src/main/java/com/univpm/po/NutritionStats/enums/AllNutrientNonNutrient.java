package com.univpm.po.NutritionStats.enums;

import com.univpm.po.NutritionStats.model.nutrient.*;

public enum AllNutrientNonNutrient {
	CARBOHYDRATE(Carbohydrate.class, "Carbohydrates", "CHOCDF"), 
	SUGAR(Carbohydrate.class, "Sugars", "SUGAR"),
	PROTEIN(Protein.class, "Proteins", "PROCNT"), 
	LIPID(Lipid.class, "Fat", "FAT"),
	SATURATED(Lipid.class, "Saturated Fat", "FASAT"), 
	VITAMIN_A(VitaminA.class, "Vitamin A", "VITA_RAE"),
	VITAMIN_C(VitaminC.class, "Vitamin C", "VITC"), 
	SODIUM(Sodium.class, "Sodium", "NA"),
	CALCIUM(Calcium.class, "Calcium", "CA"), 
	POTASSIUM(Potassium.class, "Potassium", "K"),
	IRON(Iron.class, "Iron", "FE"),

	FIBER(Fiber.class, "Fiber", "FIBTG"), WATER_FROM_FOOD(WaterFromFood.class, "Water", "WATER");

	private Class<?> referenceClass;
	private String chompKeyWord;
	private String edamamKeyWord;

	AllNutrientNonNutrient(Class<?> referenceClass, String chompKeyWord, String edamamKeyWord) {
		this.referenceClass = referenceClass;
		this.chompKeyWord = chompKeyWord;
		this.edamamKeyWord = edamamKeyWord;
	}

	public String getChompKeyWord() {
		return chompKeyWord;
	}

	public String getEdamamKeyWord() {
		return edamamKeyWord;
	}

	public Class<?> getReferenceClass() {
		return referenceClass;
	}
}
