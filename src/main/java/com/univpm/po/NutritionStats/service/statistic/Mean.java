package com.univpm.po.NutritionStats.service.statistic;

import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.utility.Mathematics;

public class Mean extends Statistic {
    private float calories = 0.0f;
    private float weight = 0.0f;

    public float getCalories() {
        return calories;
    }

    public float getWeight() {
        return weight;
    }

    /**
     * <b>Calculate the sample mean based on the sample extracted on the provided filtered diary</b>
     *
     * @param diary the instance of {@link Diary} on which the statistic will be calculated.
     * @see Mathematics#calculateSampleMean(boolean...)
     * @see Statistic#extractAllNutrientNonNutrientSamples(Diary)
     * @see Statistic#extractCalorieSample(Diary)
     * @see Statistic#extractWeightSample(Diary)
     */
    public void calculateStatistic(Diary diary) {
        var samples = extractAllNutrientNonNutrientSamples(diary);
        for (var entry : statsValues.entrySet())
            entry.setValue(new Mathematics(samples.get(entry.getKey())).calculateSampleMean());
        calories = new Mathematics(extractCalorieSample(diary)).calculateSampleMean();
        weight = new Mathematics(extractWeightSample(diary)).calculateSampleMean();
    }
}