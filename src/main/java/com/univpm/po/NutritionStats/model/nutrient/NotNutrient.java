package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.enums.Measure;

import java.io.Serializable;

/**
 * Represents a generic not nutrient which is one between fiber or water from
 * food. It has no calories. Fiber isn't considered a nutrient because it's not
 * absorbed by the human body. Water from food, instead, is absorbed but isn't
 * considered a nutrient, it only has micronutrients.
 *
 * @author Davide Mazzatenta
 */
public abstract class NotNutrient implements Serializable {
    private AllNutrientNonNutrient name;
    private Measure measure;
    private float quantity;

    /**
     * Class constructor that instantiates a not nutrient with quantity, "label" and
     * unity of measure.
     *
     * @param name     of a not nutrient
     * @param measure  the unity of measure of a not nutrient
     * @param quantity of a not nutrient
     */
    public NotNutrient(AllNutrientNonNutrient name, Measure measure, float quantity) {
        this.name = name;
        this.measure = measure;
        this.quantity = quantity;
    }

    /**
     * @return name of a not nutrient
     */
    public AllNutrientNonNutrient getName() {
        return name;
    }

    /**
     * @return quantity of a not nutrient
     */
    public float getQuantity() {
        return quantity;
    }

    /**
     * set the quantity passed by the parameter
     *
     * @param quantity to set
     */
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
