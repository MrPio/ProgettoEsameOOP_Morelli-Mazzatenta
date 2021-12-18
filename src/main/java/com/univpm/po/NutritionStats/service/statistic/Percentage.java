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

	private JSONObject jPercentages = new JSONObject();
	
	private Map<Class<?>, Float> percentageList = new HashMap<>() {{
		put(Carbohydrate.class, 0f);
        put(Lipid.class, 0f);
        put(Protein.class, 0f);
	}};
	
	public Percentage(Diary diary) {
		super(diary);
	}

	public JSONObject getjPercentages() {
		return jPercentages;
	}
	
	public Map<Class<?>, Float> getPercentageList() {
		return percentageList;
	}

	public JSONObject macroNutrientPercentage(LocalDate startDate, LocalDate endDate) throws EndDateBeforeStartDateException {
		
		checkDateException(startDate,endDate);

		int carbCalories = 0, lipidCalories = 0, protCalories = 0;
		float calories = 0;

		
		for (Day day : diary.getDayList()) {
            if (dateIsBetween(day.getDate(), startDate, endDate)) {
            	 for (Map.Entry<Class<?>, Float> entry : percentageList.entrySet())
            		 entry.setValue(entry.getValue() + day.calculate((Class<?>) entry.getKey()));
            	 calories += day.calculateCalories();
            }
		}
		return jPercentages;
		
		/*
		 * for (Map.Entry<Class<?>, Float> entry : percentageList.entrySet())
			entry.setValue((entry.getValue() * (entry.getKey().);
		
		
		
		for (Day day : diary.getDayList()) {
			if (dateIsBetween(day.getDate(), startDate, endDate)) {
				totalCalories += day.calculateCalories();
				carbCalories += day.calculate(Carbohydrate.class) * Carbohydrate.CALORIES_PER_CARBOHYDRATE;
				lipidCalories += day.calculate(Lipid.class) * Protein.CALORIES_PER_PROTEIN;
				protCalories += day.calculate(Protein.class) * Lipid.CALORIES_PER_LIPID;
			}
		}
		percentages.put("Carbohydrates", (carbCalories * 100) / totalCalories);
		percentages.put("Lipids", (lipidCalories * 100) / totalCalories);
		percentages.put("Protein", (protCalories * 100) / totalCalories);
		return percentages;
	}
	*/
	}
}