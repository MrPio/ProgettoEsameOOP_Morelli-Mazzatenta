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
	JSONObject StandardDeviationsValues = new JSONObject();
	float calories = 0;
	float weight = 0;

	public StandardDeviatiton(Diary diary) {
		super(diary);
	}

	public JSONObject getStandardDeviationsValues() {
		return StandardDeviationsValues;
	}

	public JSONObject calculateStatistic(LocalDate startDate, LocalDate endDate)
			throws EndDateBeforeStartDateException {
		if (startDate.isEqual(endDate))
			return new JSONObject(Map.of("result", "error: cannot calculate standard deviation in one day"));
		checkDateException(startDate, endDate);
		resetValues();
		calories = 0f;

		Mean mean = new Mean(diary);
		mean.calculateStatistic(startDate, endDate);

		int count = 0;
		for (Day day : diary.getDayList()) {
			if (dateIsBetween(day.getDate(), startDate, endDate)) {
				for (Map.Entry<Class<?>, Float> entry : statsValues.entrySet()) {
					float value = day.calculate((Class<?>) entry.getKey()) - mean.getStatsValues().get(entry.getKey());
					entry.setValue(entry.getValue() + (float) Math.pow(value, 2));
				}
				calories += (float) Math.pow(day.calculateCalories() - mean.calculateCalories(), 2);
				++count;
			}
		}
		
		for (Map.Entry<Class<?>, Float> entry : statsValues.entrySet()) {
			entry.setValue((float) Math.sqrt(entry.getValue() / (count - 1)));
			StandardDeviationsValues.put(entry.getKey().getSimpleName().toLowerCase(), entry.getValue());
		}

		calories = (float) Math.sqrt(calories / (count - 1));
		StandardDeviationsValues.put("calorie", calories);

		count = 0;
		for (LocalDate date : diary.getUser().getWeight().keySet()) {
			if (dateIsBetween(date, startDate, endDate)) {
				weight += (float) Math.pow(diary.getUser().getWeight().get(date) - mean.calculateWeight(), 2);
				++count;
			}
		}
		weight = (float) Math.sqrt(weight / (count - 1));
		StandardDeviationsValues.put("weight", weight);

		return StandardDeviationsValues;
	}
}
