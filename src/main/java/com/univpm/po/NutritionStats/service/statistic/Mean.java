package com.univpm.po.NutritionStats.service.statistic;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.univpm.po.NutritionStats.model.Water;
import com.univpm.po.NutritionStats.model.nutrient.*;

import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.User;

public class Mean extends Statistic {

    private JSONObject jMeans = new JSONObject();

    private Map<Class<?>, Float> meanList = new HashMap<>() {{
        put(Carbohydrate.class, 0f);
        put(Lipid.class, 0f);
        put(Protein.class, 0f);
        put(Water.class, 0f);
        put(VitaminA.class, 0f);
        put(VitaminC.class, 0f);
        put(Sodium.class, 0f);
        put(Calcium.class, 0f);
        put(Potassium.class, 0f);
        put(Iron.class, 0f);
        put(Fiber.class, 0f);
    }};
    private float calories = 0.0f;
    private float weight = 0.0f;

    public Mean(Diary diary) {
        super(diary);
    }

    public JSONObject getJMeans() {
        return jMeans;
    }

    public Map<Class<?>, Float> getMeanList() {
        return meanList;
    }

    public float getCalories() {
        return calories;
    }

    public float getWeight() {
        return weight;
    }

    public JSONObject allNutrientMean(LocalDate startDate, LocalDate endDate) throws EndDateBeforeStartDateException {
        checkDateException(startDate, endDate);
        resetValues();
        calories = 0f;

        int count = 0;
        for (Day day : diary.getDayList()) {
            if (dateIsBetween(day.getDate(), startDate, endDate)) {
                for (Map.Entry<Class<?>, Float> entry : meanList.entrySet())
                    entry.setValue(entry.getValue() + day.calculate((Class<?>) entry.getKey()));
                calories += day.calculateCalories();
                ++count;
            }
        }
        for (Map.Entry<Class<?>, Float> entry : meanList.entrySet())
            entry.setValue(entry.getValue() / count);
        calories /= count;

        for (Map.Entry<Class<?>, Float> entry : meanList.entrySet())
            jMeans.put(entry.getKey().getSimpleName().toLowerCase(), entry.getValue());
        jMeans.put("calorie", calories);

        count = 0;
        for (LocalDate date : diary.getUser().getWeight().keySet()) {
            if (dateIsBetween(date, startDate, endDate)) {
                weight += diary.getUser().getWeight().get(date);
                ++count;
            }
        }
        weight /= count;
        jMeans.put("weight", weight);

        return jMeans;
    }

    private void resetValues() {
        for (Map.Entry<Class<?>, Float> entry : meanList.entrySet())
            entry.setValue(0f);
    }
}