package com.univpm.po.NutritionStats.service;

import java.time.LocalDate;

import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;

public class StandardDeviatiton extends Statistic{

	public StandardDeviatiton(Diary diary) {
		super(diary);
	}

	public JSONObject calculateStandardDeviation(LocalDate startDate, LocalDate endDate) {
		
		JSONObject standardDeviations = new JSONObject();
		int count = 0;
		float calories = 0, carbohydrates = 0, lipid = 0, protein = 0, water = 0;
		float sdcalories = 0, sdcarbohydrates = 0, sdlipid = 0, sdprotein = 0, sdwater = 0;
		Mean mean = new Mean(diary);
		
		
		for (Day day : diary.getDayList()) {
			calories = day.calculateCalories() - (float) mean.allNutrientMean(startDate, endDate).get("Calories");
			sdcalories += Math.pow(calories,2);
			carbohydrates = day.calculateCarbohydrates() - (float) mean.allNutrientMean(startDate, endDate).get("Carbohydrates");
			sdcarbohydrates += Math.pow(carbohydrates,2);
			lipid = day.calculateLipids() - (float) mean.allNutrientMean(startDate, endDate).get("Lipids");
			sdlipid += Math.pow(lipid,2);
			protein = day.calculateProteins() - (float) mean.allNutrientMean(startDate, endDate).get("Proteins");
			sdprotein += Math.pow(calories,2);
			water = day.calculateWater() - (float) mean.allNutrientMean(startDate, endDate).get("Water");
			sdwater += Math.pow(water,2);
			count++;
		}
		
		standardDeviations.put("Calories", Math.pow((sdcalories/(count-1)),-2));
		standardDeviations.put("Carbohydrates", Math.pow((sdcarbohydrates/(count-1)),-2));
		standardDeviations.put("Lipids", Math.pow((sdlipid/(count-1)),-2));
		standardDeviations.put("Proteins", Math.pow((sdprotein/(count-1)),-2));
		standardDeviations.put("Water", Math.pow((sdwater/(count-1)),-2));
		
		return standardDeviations;
		
	}
	
}
