package com.univpm.po.NutritionStats.service.statistic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.utility.Mathematics;

public class StandardDeviation extends Statistic {
    private float calories = 0;
    private float weight = 0;

    public float getCalories() {
        return calories;
    }

    public float getWeight() {
        return weight;
    }

    public void calculateStatistic(Diary diary) {
        var samples = extractAllNutrientNonNutrientSamples(diary);
        for (var entry : statsValues.entrySet())
            entry.setValue(new Mathematics(samples.get(entry.getKey())).calculateSampleStandardDeviation());
        calories = new Mathematics(extractCalorieSample(diary)).calculateSampleStandardDeviation();
        weight = new Mathematics(extractWeightSample(diary)).calculateSampleStandardDeviation();
    }
}
