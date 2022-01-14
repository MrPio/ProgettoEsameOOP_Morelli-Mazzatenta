package com.univpm.po.NutritionStats.service.statistic;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.nutrient.MacroNutrient;
import com.univpm.po.NutritionStats.utility.Mathematics;

import java.util.HashMap;
import java.util.Map;

public class Percentage extends Statistic {
    private Map<AllNutrientNonNutrient, Float> statsValues = new HashMap<>() {
        {
            put(AllNutrientNonNutrient.CARBOHYDRATE, 0f);
            put(AllNutrientNonNutrient.LIPID, 0f);
            put(AllNutrientNonNutrient.PROTEIN, 0f);
        }
    };

    @Override
    public Map<AllNutrientNonNutrient, Float> getStatsValues() {
        return statsValues;
    }

    public void calculateStatistic(Diary diary) {
        var samples = extractAllNutrientNonNutrientSamples(diary);
        for (var entry : statsValues.entrySet())
            entry.setValue(new Mathematics(samples.get(entry.getKey())).calculateSum()
                    * MacroNutrient.CALORIES_PER_GRAM.get(entry.getKey().getReferenceClass()) * 100f
                    / new Mathematics(extractCalorieSample(diary)).calculateSum());
    }
}