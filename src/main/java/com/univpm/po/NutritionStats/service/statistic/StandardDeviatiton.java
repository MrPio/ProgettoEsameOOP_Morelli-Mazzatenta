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

	private Map<Class<?>, Float> standardDeviationList = new HashMap<>() {
		{
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
		}
	};
	
	JSONObject jStandardDeviations = new JSONObject();
	float calories = 0;
	float weight = 0;

	public StandardDeviatiton(Diary diary) {
		super(diary);
	}

	public JSONObject getjStandardDeviations() {
		return jStandardDeviations;
	}

	public JSONObject calculateStandardDeviation(LocalDate startDate, LocalDate endDate)
			throws EndDateBeforeStartDateException {
		checkDateException(startDate, endDate);
		if (startDate.isEqual(endDate))
			return new JSONObject(Map.of("result", "error: cannot calculate standard deviation in one day"));
		resetValues();
		calories = 0f;

		Mean mean = new Mean(diary);
		mean.allNutrientMean(startDate, endDate);

		int count = 0;
		for (Day day : diary.getDayList()) {
			if (dateIsBetween(day.getDate(), startDate, endDate)) {
				for (Map.Entry<Class<?>, Float> entry : standardDeviationList.entrySet()) {
					float value = day.calculate((Class<?>) entry.getKey()) - mean.getMeanList().get(entry.getKey());
					entry.setValue(entry.getValue() + (float) Math.pow(value, 2));
				}
				calories += (float) Math.pow(day.calculateCalories() - mean.getCalories(), 2);
				++count;
			}
		}
		
		for (Map.Entry<Class<?>, Float> entry : standardDeviationList.entrySet()) {
			entry.setValue((float) Math.sqrt(entry.getValue() / (count - 1)));
			jStandardDeviations.put(entry.getKey().getSimpleName().toLowerCase(), entry.getValue());
		}

		calories = (float) Math.sqrt(calories / (count - 1));
		jStandardDeviations.put("calorie", calories);

		count = 0;
		for (LocalDate date : diary.getUser().getWeight().keySet()) {
			if (dateIsBetween(date, startDate, endDate)) {
				weight += (float) Math.pow(diary.getUser().getWeight().get(date) - mean.getWeight(), 2);
				++count;
			}
		}
		weight = (float) Math.sqrt(weight / (count - 1));
		jStandardDeviations.put("weight", weight);

		return jStandardDeviations;
	}

	private void resetValues() {
		for (Map.Entry<Class<?>, Float> entry : standardDeviationList.entrySet())
			entry.setValue(0f);
	}
}
