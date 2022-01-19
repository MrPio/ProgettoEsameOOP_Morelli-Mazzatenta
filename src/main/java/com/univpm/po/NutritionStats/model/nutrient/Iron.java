package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

/**
 * Represent a specified mineral salt, the iron
 *
 * @author Davide Mazzatenta
 */
public class Iron extends MineralSalt implements Serializable {

    /**
     * Class constructor that instantiates iron with quantity and "label"
     *
     * @param quantity of iron
     */
    public Iron(float quantity) {
        super(AllNutrientNonNutrient.IRON, quantity);
    }

}
