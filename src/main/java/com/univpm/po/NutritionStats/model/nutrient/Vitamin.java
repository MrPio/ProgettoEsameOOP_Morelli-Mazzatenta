package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represents a generic vitamin which is a micronutrient and can be one between
 * vitamin A and vitamin C.
 *
 * @author Davide Mazzatenta
 */
public abstract class Vitamin extends MicroNutrient implements Serializable {

    /**
     * Class constructor that instantiates a vitamin with quantity and "label".
     *
     * @param name     of the vitamin which is one between vitaminA and vitaminC
     * @param quantity of vitamin
     */
    public Vitamin(AllNutrientNonNutrient name, float quantity) {
        super(name, quantity);
    }

}
