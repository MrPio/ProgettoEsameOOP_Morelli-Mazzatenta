package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.NotNutrientName;
import com.univpm.po.NutritionStats.enums.NutrientName;

import java.io.Serializable;

public abstract class NotNutrient implements Serializable {
    private NotNutrientName name;
    private Measure measure;
    private float quantity;

    public NotNutrient(NotNutrientName name, Measure measure, float quantity) {
        this.name = name;
        this.measure = measure;
        this.quantity = quantity;
    }

    public NotNutrientName getName() {
        return name;
    }

    public float getQuantity() {
        return quantity;
    }
}
