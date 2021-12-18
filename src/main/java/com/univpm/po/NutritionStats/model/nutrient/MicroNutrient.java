package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public abstract class MicroNutrient extends Nutrient implements Serializable {

    public MicroNutrient(NutrientName name, float quantity) {
        super(name, quantity, Measure.GR);
    }
}
