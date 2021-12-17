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
		
		if (startDate.isEqual(endDate))
			return null;
		
		JSONObject jStandardDeviations = new JSONObject();
		float calories = 0, carbohydrates = 0, lipid = 0, protein = 0, water = 0;
		float sdcalories = 0, sdcarbohydrates = 0, sdlipid = 0, sdprotein = 0, sdwater = 0;
		Mean mean = new Mean(diary);
		
		mean.allNutrientMean(startDate, endDate);
		
		for (Day day : diary.getDayList()) {
			calories = day.calculateCalories() - (float) mean.getJmeans().get("Calories");
			sdcalories += Math.pow(calories,2);
			carbohydrates = day.calculateCarbohydrates() - (float) mean.getJmeans().get("Carbohydrates");
			sdcarbohydrates += Math.pow(carbohydrates,2);
			lipid = day.calculateLipids() - (float) mean.getJmeans().get("Lipids");
			sdlipid += Math.pow(lipid,2);
			protein = day.calculateProteins() - (float) mean.getJmeans().get("Proteins");
			sdprotein += Math.pow(calories,2);
			water = day.calculateWater() - (float) mean.getJmeans().get("Water");
			sdwater += Math.pow(water,2);
		}
		
		jStandardDeviations.put("Calories", Math.sqrt((sdcalories/(diary.getSize()-1))));
		jStandardDeviations.put("Carbohydrates", Math.sqrt((sdcarbohydrates/(diary.getSize()-1))));
		jStandardDeviations.put("Lipids", Math.sqrt((sdlipid/(diary.getSize()-1))));
		jStandardDeviations.put("Proteins", Math.sqrt((sdprotein/(diary.getSize()-1))));
		jStandardDeviations.put("Water", Math.sqrt((sdwater/(diary.getSize()-1))));
		
		return jStandardDeviations;
		
	}
	
}
