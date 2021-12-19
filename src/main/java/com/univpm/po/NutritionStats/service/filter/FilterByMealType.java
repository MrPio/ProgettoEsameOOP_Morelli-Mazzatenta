package com.univpm.po.NutritionStats.service.filter;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Meal;

public class FilterByMealType extends Filter {
	
	public FilterByMealType(JSONObject diary) {
		super(diary);
	}
	
	public JSONObject filter(MealType mealType) {
		
		ArrayList<Day> dayList = ((Diary) diary.get("diary")).getDayList();
		JSONArray filteredList = new JSONArray();
		
		for (Day day : dayList) {
			ArrayList<Meal> mealList = day.getMealList();
			for (Meal meal : mealList) {
				MealType typeOfMeal = meal.getMealType();
				if(mealType == typeOfMeal)
					filteredList.add(meal);
			}
		}
		filteredData.put("mealList", filteredList);
		return filteredData;
	}

}
