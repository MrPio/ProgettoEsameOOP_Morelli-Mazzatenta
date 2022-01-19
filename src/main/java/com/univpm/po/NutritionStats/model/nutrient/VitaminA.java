package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a specified vitamin, the vitamin A.
 *
 * @author Davide Mazzatenta
 */
public class VitaminA extends Vitamin implements Serializable {

    /**
     * Class constructor that instantiates vitamin A with quantity and "label".
     *
     * @param quantity of vitaminA
     */
    public VitaminA(float quantity) {
        super(AllNutrientNonNutrient.VITAMIN_A, quantity);
    }
}
