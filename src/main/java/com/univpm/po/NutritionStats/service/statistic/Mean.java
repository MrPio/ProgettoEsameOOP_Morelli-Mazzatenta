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

	private JSONObject meansValues = new JSONObject();

	private float calories = 0.0f;
	private float weight = 0.0f;

	public JSONObject getMeansValues() {
		return meansValues;
	}

	public float calculateCalories() {
		return calories;
	}

	public float calculateWeight() {
		return weight;
	}

	public void calculateStatistic(Diary diary) {
		resetValues();
		calories = 0f;

		int count = 0;
		for (Day day : diary.getDayList()) {
				for (Map.Entry<Class<?>, Float> entry : statsValues.entrySet())
					entry.setValue(entry.getValue() + day.calculate((Class<?>) entry.getKey()));
				calories += day.calculateCalories();
				++count;
			}
		
		for (Map.Entry<Class<?>, Float> entry : statsValues.entrySet()) {
			entry.setValue(entry.getValue() / count);
			meansValues.put(entry.getKey().getSimpleName().toLowerCase(), entry.getValue());
		}
		
		calories /= count;
		meansValues.put("calorie", calories);
		
		count = 0;
		for (LocalDate date : diary.getUser().getWeight().keySet()) {
				weight += diary.getUser().getWeight().get(date);
				++count;
		}
		weight /= count;
		meansValues.put("weight", weight);
	}

}