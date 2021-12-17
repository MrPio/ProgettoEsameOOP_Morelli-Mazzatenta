package com.univpm.po.NutritionStats.service;

import java.time.LocalDate;

import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.User;

public class StandardDeviatiton extends Statistic {

	public StandardDeviatiton(Diary diary, User user) {
		super(diary, user);
	}

	public JSONObject calculateStandardDeviation(LocalDate startDate, LocalDate endDate) throws EndDateBeforeStartDateException {

		throwDateException(startDate,endDate);
		if (startDate.isEqual(endDate))
			return null;

		JSONObject jStandardDeviations = new JSONObject();
		float calories = 0, carbohydrates = 0, lipid = 0, protein = 0, water = 0;
		float sdcalories = 0, sdcarbohydrates = 0, sdlipid = 0, sdprotein = 0, sdwater = 0;
		Mean mean = new Mean(diary, user);

		mean.allNutrientMean(startDate, endDate);

		for (Day day : diary.getDayList()) {
			if (dateIsBetween(day.getDate(), startDate, endDate)) {
				calories = day.calculateCalories() - (float) mean.getJMeans().get("Calories");
				sdcalories += Math.pow(calories, 2);
				carbohydrates = day.calculateCarbohydrates() - (float) mean.getJMeans().get("Carbohydrates");
				sdcarbohydrates += Math.pow(carbohydrates, 2);
				lipid = day.calculateLipids() - (float) mean.getJMeans().get("Lipids");
				sdlipid += Math.pow(lipid, 2);
				protein = day.calculateProteins() - (float) mean.getJMeans().get("Proteins");
				sdprotein += Math.pow(calories, 2);
				water = day.calculateWater() - (float) mean.getJMeans().get("Water");
				sdwater += Math.pow(water, 2);
			}
		}

		jStandardDeviations.put("Calories", Math.sqrt((sdcalories / (diary.getSize() - 1))));
		jStandardDeviations.put("Carbohydrates", Math.sqrt((sdcarbohydrates / (diary.getSize() - 1))));
		jStandardDeviations.put("Lipids", Math.sqrt((sdlipid / (diary.getSize() - 1))));
		jStandardDeviations.put("Proteins", Math.sqrt((sdprotein / (diary.getSize() - 1))));
		jStandardDeviations.put("Water", Math.sqrt((sdwater / (diary.getSize() - 1))));

		return jStandardDeviations;

	}

	public JSONObject weightStandardDeviation(LocalDate startDate, LocalDate endDate) throws EndDateBeforeStartDateException {

		throwDateException(startDate,endDate);
		if (startDate.isEqual(endDate))
			return null;
		
		JSONObject jWeightStandardDeviation = new JSONObject();
		Mean mean = new Mean(diary, user);
		float weight = 0;
		float sdweight = 0;
		
		mean.weightMean(startDate, endDate);

		for (LocalDate date : user.getWeight().keySet()) {
			if (dateIsBetween(date, startDate, endDate)) {
				weight = user.getWeight().get(date) - (float) mean.getWeightMean().get("weight");
				sdweight += Math.pow(weight, 2);
			}
		}
		jWeightStandardDeviation.put("Weight", Math.sqrt((sdweight) / (user.getWeight().size() - 1)));
		return jWeightStandardDeviation;
	}

}
