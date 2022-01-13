package com.univpm.po.NutritionStats.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.univpm.po.NutritionStats.enums.MealType;

/**
 * Represents a meal in a day which can be breakfast, lunch, snack or dinner
 * 
 * @author Davide
 *
 */
public class Meal implements Serializable {

	private MealType mealType;
	private ArrayList<Food> foodList;

	/**
	 * Class first constructor
	 * 
	 * @param mealType
	 */
	public Meal(MealType mealType) {
		this.mealType = mealType;
		this.foodList = new ArrayList<>();
	}

	/**
	 * Class second constructor
	 * 
	 * @param mealType
	 * @param foodList
	 * @param waterList
	 */
	public Meal(MealType mealType, ArrayList<Food> foodList, ArrayList<Water> waterList) {
		this.mealType = mealType;
		this.foodList = foodList;
	}

	/**
	 * @return the meal type between breakfast, lunch, snack and dinner
	 */
	public MealType getMealType() {
		return mealType;
	}

	/**
	 * @return the food list of a meal
	 */
	public ArrayList<Food> getFoodList() {
		return foodList;
	}

	/**
	 * Add food to a meal
	 * 
	 * @param food to add
	 */
	public void addFood(Food food) {
		foodList.add(food);
	}

	/**
	 * Calculate the total calories of a meal through foods
	 * 
	 * @return meal total calories
	 */
	public int getTotalCalories() {
		int calories = 0;
		for (Food food : foodList)
			calories += food.getTotalCalories();
		return calories;
	}

	/**
	 * Calculate the quantity of nutrient and not nutrient in a meal. It's based on
	 * the calculate method in Food Class
	 * 
	 * @param <T>
	 * @param myClass
	 * @return nutrient or not nutrient quantity
	 */
	public <T> float calculate(Class<T> myClass) {
		float value = 0;
		for (Food food : foodList)
			value += food.calculate(myClass);
		return value;
	}
}
