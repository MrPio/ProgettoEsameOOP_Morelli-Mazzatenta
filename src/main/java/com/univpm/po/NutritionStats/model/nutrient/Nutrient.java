package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represents a generic nutrient.
 * @author Davide
 * 
 */
public abstract class Nutrient implements Serializable {
	private AllNutrientNonNutrient name;
	private Measure measure;
	private float quantity;

	/**
	 * Class constructor
	 * @param name
	 * @param quantity
	 * @param measure
	 */
	public Nutrient(AllNutrientNonNutrient name, float quantity, Measure measure) {
		this.name = name;
		this.measure = measure;
		this.quantity = quantity;
	}

	/**
	 * @return the name of a nutrient
	 */
	public AllNutrientNonNutrient getName() {
		return name;
	}

	/**
	 * @return the unity of measure of a nutrient
	 */
	public Measure getMeasure() {
		return measure;
	}

    /**
     * @return quantity of a nutrient 
     */
	public float getQuantity() {
		return quantity;
	}

    /**
     * set the quantity passed by the param
     * @param quantity
     */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
}
