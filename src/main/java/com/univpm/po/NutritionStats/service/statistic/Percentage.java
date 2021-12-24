package com.univpm.po.NutritionStats.service.statistic;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.nutrient.MacroNutrient;

import java.util.HashMap;
import java.util.Map;

public class Percentage extends Statistic {
	private Map<AllNutrientNonNutrient, Float> statsValues = new HashMap<>() {
		{
			put(AllNutrientNonNutrient.CARBOHYDRATE, 0f);
			put(AllNutrientNonNutrient.LIPID, 0f);
			put(AllNutrientNonNutrient.PROTEIN, 0f);
		}
	};

    @Override
    public Map<AllNutrientNonNutrient, Float> getStatsValues() {
        return statsValues;
    }

    public void calculateStatistic(Diary diary) {
        resetValues();

        float calories = 0;

        for (Day day : diary.getDayList()) {
                for (Map.Entry<AllNutrientNonNutrient, Float> entry : statsValues.entrySet())
                    entry.setValue(entry.getValue() + day.calculate(entry.getKey().getReferenceClass()));
                calories += day.getTotalCalories();
        }

        for (Map.Entry<AllNutrientNonNutrient, Float> entry : statsValues.entrySet())
            entry.setValue((entry.getValue() * MacroNutrient.CALORIES_PER_GRAM.get(entry.getKey().getReferenceClass()) * 100f) / calories);
    }
}