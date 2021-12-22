package com.univpm.po.NutritionStats.service.statistic;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.univpm.po.NutritionStats.model.Water;
import com.univpm.po.NutritionStats.model.nutrient.*;

import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;

public class StandardDeviatiton extends Statistic {
    JSONObject standardDeviationsValues = new JSONObject();
    float calories = 0;
    float weight = 0;

    public JSONObject getstandardDeviationsValues() {
        return standardDeviationsValues;
    }

    public void calculateStatistic(Diary diary) {
      
        resetValues();
        calories = 0f;

        Mean mean = new Mean();
        mean.calculateStatistic(diary);

        int count = 0;
        for (Day day : diary.getDayList()) {
                for (Map.Entry<Class<?>, Float> entry : statsValues.entrySet()) {
                    float value = day.calculate((Class<?>) entry.getKey()) - mean.getStatsValues().get(entry.getKey());
                    entry.setValue(entry.getValue() + (float) Math.pow(value, 2));
                }
                calories += (float) Math.pow(day.calculateCalories() - mean.calculateCalories(), 2);
                ++count;
            }

        for (Map.Entry<Class<?>, Float> entry : statsValues.entrySet()) {
            entry.setValue((float) Math.sqrt(entry.getValue() / (count - 1)));
            standardDeviationsValues.put(entry.getKey().getSimpleName().toLowerCase(), entry.getValue());
        }

        calories = (float) Math.sqrt(calories / (count - 1));
        standardDeviationsValues.put("calorie", calories);

        count = 0;
        for (LocalDate date : diary.getUser().getWeight().keySet()) {
                weight += (float) Math.pow(diary.getUser().getWeight().get(date) - mean.calculateWeight(), 2);
                ++count;
        }
        weight = (float) Math.sqrt(weight / (count - 1));
        standardDeviationsValues.put("weight", weight);
    }
}
