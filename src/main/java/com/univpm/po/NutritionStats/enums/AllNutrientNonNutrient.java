package com.univpm.po.NutritionStats.enums;

import com.univpm.po.NutritionStats.model.nutrient.*;

/**
 * Represents a "label" of every nutrient and not nutrient with the respective
 * class and chomp and edamam API names as a string.
 * 
 * @author Davide
 *
 */
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
	FIBER(Fiber.class, "Fiber", "FIBTG"),
	WATER_FROM_FOOD(WaterFromFood.class, "Water", "WATER");

	private Class<?> referenceClass;
	private String chompKeyWord;
	private String edamamKeyWord;

	/**
	 * Class constructor that instantiates a "label" for a nutrient or not nutrient
	 * with the respective class, chomp API name as a string and edamam API name as
	 * a string.
	 * 
	 * @param referenceClass it can be an unknown type through generics and with
	 *                       unbounded wildcard <?>
	 * @param chompKeyWord   chomp API name as a string
	 * @param edamamKeyWord  edamam API name as a string
	 */
	AllNutrientNonNutrient(Class<?> referenceClass, String chompKeyWord, String edamamKeyWord) {
		this.referenceClass = referenceClass;
		this.chompKeyWord = chompKeyWord;
		this.edamamKeyWord = edamamKeyWord;
	}

	/**
	 * @return chomp API name as a string
	 */
	public String getChompKeyWord() {
		return chompKeyWord;
	}

	/**
	 * @return edamam API name as a string
 	 */
	public String getEdamamKeyWord() {
		return edamamKeyWord;
	}

	/**
	 * @return respective class of a nutrient or not nutrient
	 */
	public Class<?> getReferenceClass() {
		return referenceClass;
	}
}
