package com.univpm.po.NutritionStats.service;

import java.time.LocalDate;

import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;

public class Mean extends Statistic {

	public Mean(Diary diary) {
		super(diary);
	}

	public JSONObject allNutrientMean (LocalDate startDate, LocalDate endDate) {
		
		JSONObject means = new JSONObject();
		int count = 0;
		float calories = 0, carbohydrates = 0, lipid = 0, protein = 0, water = 0, vitaminA = 0, vitaminC = 0, sodium = 0, calcium = 0, potassium = 0, iron = 0, fiber = 0;
		
		for (Day day : diary.getDayList()) {
			if(isBetween(day, startDate, endDate)) {
				calories += day.calculateCalories();
				carbohydrates += day.calculateCarbohydrates();
				lipid += day.calculateLipids();
				protein += day.calculateProteins();
				water += day.calculateWater();
				vitaminA += day.calculateVitaminA();
				vitaminC += day.calculateVitaminC();
				sodium += day.calculateSodium();
				calcium += day.calculateCalcium();
				potassium += day.calculatePotassium();
				iron += day.calculateIron();
				fiber += day.calculateFiber();
				count++;
			}
		}
		means.put("Calories",(Float)calories/count);
		means.put("Carbohydrates",(Float)carbohydrates/count);
		means.put("Lipids",(Float)lipid/count);
		means.put("Proteins",(Float)protein/count);
		means.put("Water",(Float)water/count);
		means.put("vitaminA",(Float)vitaminA/count);
		means.put("vitaminC",(Float)vitaminC/count);
		means.put("sodium",(Float)sodium/count);
		means.put("calcium",(Float)calcium/count);
		means.put("potassium",(Float)potassium/count);
		means.put("iron",(Float)iron/count);
		means.put("fibers",(Float)fiber/count);				
		return means;
	}

	
}
