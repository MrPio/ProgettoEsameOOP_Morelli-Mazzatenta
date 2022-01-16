package com.univpm.po.NutritionStats.service.filter;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.Meal;

/**
 * Represents a specific type of filter which is filter by food. Filter by food
 * means that you remove all the other foods from diary.
 * 
 * @author Davide
 *
 */
public class FilterByFood extends Filter {

	private String foodName;

	/**
	 * Class constructor that instantiates a filter by food with the food you want
	 * to filter.
	 * 
	 * @param foodName name of the food you want to filter
	 */
	public FilterByFood(String foodName) {
		this.foodName = foodName;
	}

	/**
	 * Removes food on diary so you get a diary with only that specific food you
	 * wanted to filter. It checks every day on the day list, every meal in a day
	 * and every food on the food list of the meal. If it finds the same food you
	 * want to filter it doesn't remove it, otherwise yes.
	 */
	@Override
	public void filter(Diary diary) {
		for (Day day : diary.getDayList())
			for (Meal meal : day.getMealList())
				meal.getFoodList().removeIf(food -> !(food.getName().equals(foodName)));
	}
}
