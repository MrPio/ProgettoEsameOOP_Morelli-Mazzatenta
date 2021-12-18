package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

public abstract class MicroNutrient extends Nutrient implements Serializable {

    public MicroNutrient(AllNutrientNonNutrient name, float quantity) {
        super(name, quantity, Measure.GR);
    }
}
