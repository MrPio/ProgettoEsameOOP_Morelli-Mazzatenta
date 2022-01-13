package com.univpm.po.NutritionStats.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.enums.MealType;

/**
 * Represents a day with date, meals and water
 * 
 * @author Davide
 *
 */
public class Day implements Serializable {
	private LocalDate date;
	private ArrayList<Meal> mealList;
	private ArrayList<Water> waterList;
	private Map<AllNutrientNonNutrient, Float> sumValues = new HashMap<>() {
	};

	/**
	 * Class constructor
	 * 
	 * @param date
	 */
	public Day(LocalDate date) {
		this.date = date;
		this.mealList = new ArrayList<>();
		this.waterList = new ArrayList<>();
	}

	/**
	 * @return the day date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @return the water drunk in a day
	 */
	public ArrayList<Water> getWaterList() {
		return waterList;
	}

	/**
	 * @return the day date formatted in a string
	 */
	public String calculateDayId() {
		return Diary.formatter.format(date);
	}

	/**
	 * @return the day meals
	 */
	public ArrayList<Meal> getMealList() {
		return mealList;
	}

	/**
	 * Puts in a map nutrients or not nutrients with their quantity in a day
	 * 
	 * @return the daily quantity of nutrients or not nutrients
	 */
	public Map<AllNutrientNonNutrient, Float> getSumValues() {
		for (var nutrient : AllNutrientNonNutrient.values())
			if (calculate(nutrient.getReferenceClass()) > 0.0000001f && nutrient != AllNutrientNonNutrient.SUGAR
					&& nutrient != AllNutrientNonNutrient.SATURATED)
				sumValues.put(nutrient, calculate(nutrient.getReferenceClass()));
		return sumValues;
	}

	/**
	 * Adds food to a specific meal in a day and if there isn't that type of meal it
	 * creates one and adds the food.
	 * 
	 * @param mealType
	 * @param food
	 */
	public void addFood(MealType mealType, Food food) {
		for (Meal meal : mealList)
			if (meal.getMealType().equals(mealType)) {
				meal.addFood(food);
				return;
			}
		Meal newMeal = new Meal(mealType);
		newMeal.addFood(food);
		mealList.add(newMeal);
	}

	/**
	 * Adds water to the water list
	 * 
	 * @param water
	 */
	public void addWater(Water water) {
		waterList.add(water);
	}

	/**
	 * Calculates the total calories of a day through meals
	 * 
	 * @return the day total calories
	 */
	public float getTotalCalories() {
		float calories = 0;
		for (Meal meal : mealList)
			calories += meal.getTotalCalories();
		return calories;
	}

	/**
	 * Calculate the quantity of nutrient and not nutrient or the volume of water in
	 * a day. It's based on the calculate method in Meal Class
	 * 
	 * @param <T>
	 * @param myClass
	 * @return
	 */
	public <T> float calculate(Class<T> myClass) {
		float value = 0;
		for (Meal meal : mealList)
			value += meal.calculate(myClass);
		if (myClass == Water.class)
			for (Water water : waterList)
				value += water.getVolume();
		return value;
	}
}
