package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.enums.Measure;

import java.io.Serializable;

/**
 * Represents a generic micronutrient which is the part of nutrients with no
 * calories. It can be a vitamin or a mineral salt.
 *
 * @author Davide Mazzatenta
 */
public abstract class MicroNutrient extends Nutrient implements Serializable {

    /**
     * Class constructor that instantiates a micronutrient with quantity, "label" and
     * unity of measure which is grams.
     *
     * @param name     of a micronutrient that can be a vitamin or a mineral salt
     * @param quantity of a micronutrient
     */
    public MicroNutrient(AllNutrientNonNutrient name, float quantity) {
        super(name, quantity, Measure.GR);
    }
}
