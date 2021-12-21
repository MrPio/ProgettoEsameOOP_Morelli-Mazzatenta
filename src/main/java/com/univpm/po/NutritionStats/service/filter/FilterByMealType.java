package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Meal;

public class FilterByMealType extends Filter {
	
	private MealType mealType;
	
	public FilterByMealType(Diary diary, MealType mealType) {
		super(diary);
		this.mealType = mealType;
	}
	
	@Override
	public void filter() {
		
		for (Day day : diary.getDayList()) 
			for (Meal meal : day.getMealList()) 
				if(!(mealType == meal.getMealType())) 
					day.getMealList().remove(meal);
	
	}

}
