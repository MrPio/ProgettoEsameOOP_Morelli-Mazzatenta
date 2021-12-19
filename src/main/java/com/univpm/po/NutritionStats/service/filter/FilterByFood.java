package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.Meal;

public class FilterByFood extends Filter {

	public FilterByFood(JSONObject diary) {
		super(diary);
	}

	public JSONObject filter(String foodName) {
		
		ArrayList<Day> dayList = ((Diary) diary.get("diary")).getDayList(); 
		JSONArray filteredList = new JSONArray();
		
		for (Day day : dayList) {
			ArrayList<Meal> mealList = day.getMealList();
			for (Meal meal : mealList) {
				ArrayList<Food> foodList = meal.getFoodList();
				for (Food food : foodList) {
					if (food.getName().equals(foodName)) {
						filteredList.add(day.getDate());
						filteredList.add(meal.getMealType());
						filteredList.add(food);
					}
				}
			}
		}
		
		filteredData.put("foodList", filteredList);
		return filteredData;
	}
}
