package com.univpm.po.NutritionStats.service.statistic;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.utility.Mathematics;

import java.util.Map;

public class Correlation extends Statistic {

    private float correlation=0.0f;

    @Override
    public Map<AllNutrientNonNutrient, Float> getStatsValues() {
        return null;
    }

    public float getCorrelation() {
        return correlation;
    }

    /**
     * <b>Calculate the correlation between calories and weight tendency based on the sample extracted on the provided filtered diary</b>
     * @param diary the instance of {@link Diary} on which the statistic will be calculated.
     * @see Mathematics#calculateCorrelation()
     * @see Statistic#extractCalorieSample(Diary)
     * @see Statistic#extractWeightSample(Diary)
     */
    public void calculateStatistic(Diary diary) {
        correlation=new Mathematics(extractCalorieSample(diary),extractWeightSample(diary)).calculateCorrelation();
    }
}
