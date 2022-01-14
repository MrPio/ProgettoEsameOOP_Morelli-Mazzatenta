package com.univpm.po.NutritionStats.service.statistic;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.utility.Mathematics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class Mean extends Statistic {
    private float calories = 0.0f;
    private float weight = 0.0f;

    public float getCalories() {
        return calories;
    }

    public float getWeight() {
        return weight;
    }

    public void calculateStatistic(Diary diary) {
        var samples = extractAllNutrientNonNutrientSamples(diary);
        for (var entry : statsValues.entrySet())
            entry.setValue(new Mathematics(samples.get(entry.getKey())).calculateSampleMean());
        calories = new Mathematics(extractCalorieSample(diary)).calculateSampleMean();
        weight = new Mathematics(extractWeightSample(diary)).calculateSampleMean();
    }
}