package com.univpm.po.NutritionStats.service.statistic;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.utility.Mathematics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Statistic {
    protected Map<AllNutrientNonNutrient, Float> statsValues = new HashMap<>() {
        {
            for (AllNutrientNonNutrient nutrient : AllNutrientNonNutrient.values())
                if (nutrient != AllNutrientNonNutrient.SUGAR && nutrient != AllNutrientNonNutrient.SATURATED)
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

    protected Map<AllNutrientNonNutrient, ArrayList<Float>> extractAllNutrientNonNutrientSamples(Diary diary){
        Map<AllNutrientNonNutrient, ArrayList<Float>> result =new HashMap<>();
        for (var entry : statsValues.entrySet()) {
            ArrayList<Float> values = new ArrayList<>();
            for (var day : diary.getDayList())
                values.add(day.calculate(entry.getKey().getReferenceClass()));
            result.put(entry.getKey(),values);
        }
        return result;
    }

    protected ArrayList<Float> extractCalorieSample(Diary diary){
        ArrayList<Float> values = new ArrayList<>();
        for (var day : diary.getDayList())
            values.add(day.getTotalCalories());
         return values;
    }

    protected ArrayList<Float> extractWeightSample(Diary diary){
        ArrayList<Float> values = new ArrayList<>();
        for (var date : diary.getUser().getWeight().keySet())
            values.add(diary.getUser().getWeight().get(date));
        return values;
    }
}
