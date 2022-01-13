package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * @author Davide
 * Represents a generic not nutrient.
 */
public abstract class NotNutrient implements Serializable {
    private AllNutrientNonNutrient name;
    private Measure measure;
    private float quantity;
/**
 * Class constructor
 * @param name
 * @param measure
 * @param quantity
 */
    public NotNutrient(AllNutrientNonNutrient name, Measure measure, float quantity) {
        this.name = name;
        this.measure = measure;
        this.quantity = quantity;
    }

    public AllNutrientNonNutrient getName() {
        return name;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
