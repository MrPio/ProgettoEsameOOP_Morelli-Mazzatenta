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

	private String foodName;
	
	public FilterByFood(Diary diary, String foodName) {
		super(diary);
		this.foodName = foodName;
	}

	@Override
	public void filter() {
		
		for (Day day : diary.getDayList()) 
			for (Meal meal : day.getMealList()) 
				for (Food food : meal.getFoodList()) 
					if (!(food.getName().equals(foodName))) 
						meal.getFoodList().remove(food);
				
			
		
	}
}
