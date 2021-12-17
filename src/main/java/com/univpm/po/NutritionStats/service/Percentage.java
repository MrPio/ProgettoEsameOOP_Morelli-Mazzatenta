package com.univpm.po.NutritionStats.service;

import java.time.LocalDate;

import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.nutrient.*;

public class Percentage extends Statistic{

	public Percentage(Diary diary) {
		super(diary);
	}

	public JSONObject MacroNutrientPercentage (LocalDate startDate, LocalDate endDate) {
		
		JSONObject percentages = new JSONObject();
		int carbCalories = 0, lipidCalories = 0, protCalories = 0; 
		int totalCalories = 0;
		
		for (Day day : diary.getDayList()) {
			if(dayIsBetween(day,startDate,endDate)) {
				totalCalories += day.calculateCalories();
				carbCalories += day.calculateCarbohydrates() * Carbohydrate.CALORIES_PER_CARBOHYDRATE;
				lipidCalories += day.calculateLipids() * Protein.CALORIES_PER_PROTEIN;
				protCalories += day.calculateProteins() * Lipid.CALORIES_PER_LIPID;
			}
		}
		percentages.put("Carbohydrates",(carbCalories * 100)/totalCalories);
		percentages.put("Lipids",(lipidCalories * 100)/totalCalories);
		percentages.put("Protein",(protCalories * 100)/totalCalories);
		return percentages;
	}
}
