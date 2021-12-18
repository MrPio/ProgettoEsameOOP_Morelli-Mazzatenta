package com.univpm.po.NutritionStats.model.nutrient;

import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.enums.NotNutrientName;

import java.io.Serializable;

public class WaterFromFood extends NotNutrient implements Serializable {
    public WaterFromFood(float quantity) {
        super(NotNutrientName.WATER_FROM_FOOD, Measure.ML,quantity);
    }
}
