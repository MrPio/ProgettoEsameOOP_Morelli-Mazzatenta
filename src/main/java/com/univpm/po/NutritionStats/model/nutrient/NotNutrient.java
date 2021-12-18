package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import java.io.Serializable;

public abstract class NotNutrient implements Serializable {
    private AllNutrientNonNutrient name;
    private Measure measure;
    private float quantity;

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
}
