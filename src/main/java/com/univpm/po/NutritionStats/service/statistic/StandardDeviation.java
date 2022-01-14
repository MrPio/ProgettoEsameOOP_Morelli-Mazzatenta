package com.univpm.po.NutritionStats.service.statistic;

import java.time.LocalDate;
import java.util.Map;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;

public class StandardDeviation extends Statistic {
    float calories = 0;
    float weight = 0;

    public float getCalories() {
        return calories;
    }

    public float getWeight() {
        return weight;
    }

    public void calculateStatistic(Diary diary) {
        resetValues();
        calories = 0f;

        /*Mean mean = new Mean();
        mean.calculateStatistic(diary);

        int count = 0;
        for (Day day : diary.getDayList()) {
            for (Map.Entry<AllNutrientNonNutrient, Float> entry : statsValues.entrySet()) {
                float value = day.calculate(entry.getKey().getReferenceClass()) - mean.getStatsValues().get(entry.getKey());
                entry.setValue(entry.getValue() + (float) Math.pow(value, 2));
            }
            calories += (float) Math.pow(day.getTotalCalories() - mean.getCalories(), 2);
            ++count;
        }

        for (Map.Entry<AllNutrientNonNutrient, Float> entry : statsValues.entrySet())
            entry.setValue((float) Math.sqrt(entry.getValue() / (count - 1)));

        calories = (float) Math.sqrt(calories / (count - 1));

        count = 0;
        for (LocalDate date : diary.getUser().getWeight().keySet()) {
            weight += (float) Math.pow(diary.getUser().getWeight().get(date) - mean.getWeight(), 2);
            ++count;
        }
        weight = (float) Math.sqrt(weight / (count - 1));*/
    }
}
