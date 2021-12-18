package com.univpm.po.NutritionStats.service.filter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.enums.MealType;

public class FilterByMealType extends Filter {
	
	public FilterByMealType(JSONObject diary) {
		super(diary);
	}
	
	public JSONObject filter(MealType mealType) {
		
		JSONArray diaryByDay = (JSONArray) diary.get("dayList");
		JSONArray filteredList = new JSONArray();
		
		for (Object day : diaryByDay) {
			JSONArray diaryByMealList = (JSONArray) ((JSONObject) day).get("mealList");
			for (Object meals : diaryByMealList) {
				MealType typeOfMeal = (MealType) ((JSONObject)meals).get("mealType");
				if(typeOfMeal == mealType)
					filteredList.add(meals);
			}
		}
		return (JSONObject) filteredData.put("mealList", filteredList);
	}

}
