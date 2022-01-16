package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Meal;

/**
 * Represents a specific type of filter which is filter by meal type. Filter by
 * meal type means that you remove all the other meal type from diary.
 * 
 * @author Davide
 *
 */
public class FilterByMealType extends Filter {

	private MealType mealType;

	/**
	 * Class constructor that instantiates a filter by meal type with the meal type
	 * you want to filter.
	 * 
	 * @param mealType meal type you want to filter
	 */
	public FilterByMealType(MealType mealType) {
		this.mealType = mealType;
	}

	/**
	 * Removes all meal type except the one you want to filter so you get a diary
	 * with only that specific meal type. It checks every day on the day list and
	 * every meal in a day. If it finds the same meal type you want to filter it
	 * doesn't remove it, otherwise yes.
	 */
	@Override
	public void filter(Diary diary) {
		for (Day day : diary.getDayList())
			day.getMealList().removeIf(meal -> !(mealType == meal.getMealType()));
	}
}
