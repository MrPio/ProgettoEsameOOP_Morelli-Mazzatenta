package com.univpm.po.NutritionStats.service.statistic;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Diary;

import java.util.HashMap;
import java.util.Map;

public abstract class Statistic {
    protected Map<AllNutrientNonNutrient, Float> statsValues = new HashMap<>() {
        {
            for (AllNutrientNonNutrient nutrient : AllNutrientNonNutrient.values())
                put(nutrient, 0.0f);
        }
    };

    public Map<AllNutrientNonNutrient, Float> getStatsValues() {
        return statsValues;
    }

    public abstract void calculateStatistic(Diary diary);

    protected void resetValues() {
        for (Map.Entry<AllNutrientNonNutrient, Float> entry : statsValues.entrySet())
            entry.setValue(0f);
    }
}
