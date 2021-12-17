package com.univpm.po.NutritionStats.service;

import java.time.LocalDate;

import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.User;

public class Mean extends Statistic {

	private JSONObject jMeans = new JSONObject();
	private JSONObject jWeightMean = new JSONObject();

	public Mean(Diary diary, User user) {
		super(diary, user);
	}

	public JSONObject getJMeans() {
		return jMeans;
	}

	public JSONObject getWeightMean() {
		return jWeightMean;
	}
	
	public JSONObject allNutrientMean(LocalDate startDate, LocalDate endDate) throws EndDateBeforeStartDateException {

		throwDateException(startDate,endDate);
		
		float calories = 0, carbohydrates = 0, lipid = 0, protein = 0, water = 0, vitaminA = 0, vitaminC = 0,
				sodium = 0, calcium = 0, potassium = 0, iron = 0, fiber = 0;
		
		for (Day day : diary.getDayList()) {
			if (dateIsBetween(day.getDate(), startDate, endDate)) {
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
			}
		}
		jMeans.put("Calories", (Float) calories / diary.getSize());
		jMeans.put("Carbohydrates", (Float) carbohydrates / diary.getSize());
		jMeans.put("Lipids", (Float) lipid / diary.getSize());
		jMeans.put("Proteins", (Float) protein / diary.getSize());
		jMeans.put("Water", (Float) water / diary.getSize());
		jMeans.put("vitaminA", (Float) vitaminA / diary.getSize());
		jMeans.put("vitaminC", (Float) vitaminC / diary.getSize());
		jMeans.put("sodium", (Float) sodium / diary.getSize());
		jMeans.put("calcium", (Float) calcium / diary.getSize());
		jMeans.put("potassium", (Float) potassium / diary.getSize());
		jMeans.put("iron", (Float) iron / diary.getSize());
		jMeans.put("fibers", (Float) fiber / diary.getSize());
		return jMeans;
	}

	public JSONObject weightMean(LocalDate startDate, LocalDate endDate) throws EndDateBeforeStartDateException {

		throwDateException(startDate,endDate);
		float weight = 0;

		for (LocalDate date : user.getWeight().keySet())
			if (dateIsBetween(date, startDate, endDate))
				weight += user.getWeight().get(date);

		jWeightMean.put("weight", weight);
		return jWeightMean;
	}
}