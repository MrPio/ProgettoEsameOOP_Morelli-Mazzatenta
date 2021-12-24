package com.univpm.po.NutritionStats.service.statistic;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;

import java.time.LocalDate;
import java.util.Map;

public class Mean extends Statistic {
	private float calories = 0.0f;
	private float weight = 0.0f;

	public float getCalories() {
		return calories;
	}

	public float getWeight() {
		return weight;
	}

	public void calculateStatistic(Diary diary) {
		resetValues();
		calories = 0f;
		int count = 0;
		for (Day day : diary.getDayList()) {
				for (Map.Entry<AllNutrientNonNutrient, Float> entry : statsValues.entrySet())
					entry.setValue(entry.getValue() + day.calculate(entry.getKey().getReferenceClass()));
				calories += day.getTotalCalories();
				++count;
			}
		
		for (Map.Entry<AllNutrientNonNutrient, Float> entry : statsValues.entrySet())
			entry.setValue(entry.getValue() / count);
		
		calories /= count;
		
		count = 0;
		for (LocalDate date : diary.getUser().getWeight().keySet()) {
				weight += diary.getUser().getWeight().get(date);
				++count;
		}
		weight /= count;
	}

}