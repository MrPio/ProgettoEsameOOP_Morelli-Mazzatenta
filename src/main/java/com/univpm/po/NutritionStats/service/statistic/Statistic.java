package com.univpm.po.NutritionStats.service.statistic;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.User;
import org.json.simple.JSONObject;

public abstract class Statistic {
    protected Map<Class<?>, Float> statsValues = new HashMap<>() {
        {
            for (AllNutrientNonNutrient nutrient : AllNutrientNonNutrient.values())
                put(nutrient.getReferenceClass(), 0.0f);
        }
    };

    protected Map<Class<?>, Float> getStatsValues() {
        return statsValues;
    }

    public abstract  void calculateStatistic(Diary diary);

    protected void resetValues() {
        for (Map.Entry<Class<?>, Float> entry : statsValues.entrySet())
            entry.setValue(0f);
    }
}
