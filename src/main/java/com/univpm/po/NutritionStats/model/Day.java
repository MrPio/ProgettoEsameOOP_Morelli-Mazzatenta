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
 * @author Davide Mazzatenta
 *
 */
public class Day implements Serializable {
	private final LocalDate date;
	private final ArrayList<Meal> mealList;
	private final ArrayList<Water> waterList;
	private final Map<AllNutrientNonNutrient, Float> sumValues = new HashMap<>() {};

	/**
	 * Class constructor that instantiates a day with a date, a new meal list a new
	 * water list
	 * 
	 * @param date of the day
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
	 * @return the meals in a day
	 */
	public ArrayList<Meal> getMealList() {
		return mealList;
	}

	/**
	 * Puts in a map the total quantity of nutrients or not nutrients in a day.
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
	 * Adds food to a specific meal in a day and if there isn't that type of meal in
	 * the meal list it creates one and adds the food.
	 * 
	 * @param mealType of the meal: breakfast, lunch, snack or dinner
	 * @param food     to add in that meal
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
	 * @param water to add
	 */
	public void addWater(Water water) {
		waterList.add(water);
	}

	/**
	 * Calculates the total calories of a day through meals, that is based on the
	 * method in Meal class that calculate total calories of a meal through foods.
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
	 * Calculate the quantity of nutrients and not nutrients or the volume of water in
	 * a day. It's based on the method in Meal Class that calculates the quantity of
	 * nutrients and not nutrients in a meal and on the the method in Water Class
	 * that get the volume of water.
	 * 
	 * @param <T>     means that the method will be dealing with generic type
	 * @param myClass is a class object of a specific class type <T>. It can be a
	 *                String, an Integer or a Object
	 * @return nutrient and not nutrient or water quantity in a day
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
