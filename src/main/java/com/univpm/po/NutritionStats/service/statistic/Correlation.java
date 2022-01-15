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

    public void calculateStatistic(Diary diary) {
        correlation=new Mathematics(extractCalorieSample(diary),extractWeightSample(diary)).calculateCorrelation();
    }
}
