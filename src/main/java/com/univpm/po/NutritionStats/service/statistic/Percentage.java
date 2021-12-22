package com.univpm.po.NutritionStats.service.statistic;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.User;
import com.univpm.po.NutritionStats.model.nutrient.*;

public class Percentage extends Statistic {

    private JSONObject percentagesValues = new JSONObject();

	private Map<Class<?>, Float> statsValues = new HashMap<>() {
		{
			put(Carbohydrate.class, 0f);
			put(Lipid.class, 0f);
			put(Protein.class, 0f);
		}
	};

    public JSONObject getpercentagesValues() {
        return percentagesValues;
    }

    public void calculateStatistic(Diary diary) {

        resetValues();

        float calories = 0;

        for (Day day : diary.getDayList()) {
                for (Map.Entry<Class<?>, Float> entry : statsValues.entrySet())
                    entry.setValue(entry.getValue() + day.calculate((Class<?>) entry.getKey()));
                calories += day.calculateCalories();
        }

        for (Map.Entry<Class<?>, Float> entry : statsValues.entrySet()) {
            entry.setValue((entry.getValue() * MacroNutrient.CALORIES_PER_GRAM.get(entry.getKey()) * 100f) / calories);
            percentagesValues.put(entry.getKey().getSimpleName().toLowerCase(), entry.getValue());
        }
    }
}