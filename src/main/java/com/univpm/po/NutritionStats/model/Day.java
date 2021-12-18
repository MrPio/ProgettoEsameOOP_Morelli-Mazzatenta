package com.univpm.po.NutritionStats.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import com.univpm.po.NutritionStats.enums.MealType;
import org.json.simple.JSONObject;

public class Day implements Serializable {
	private LocalDate date;
	private ArrayList<Meal> mealList;

	public Day(LocalDate date) {
		this.date = date;
		this.mealList = new ArrayList<>();
	}

	public LocalDate getDate() {
		return date;
	}

	public String calculateDayId() {
		return Diary.formatter.format(date);
	}

	public ArrayList<Meal> getMealList() {
		return mealList;
	}

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

	public void addWater(MealType mealType, Water water) {
		for (Meal meal : mealList)
			if (meal.getMealType().equals(mealType)) {
				meal.addWater(water);
				return;
			}
		Meal newMeal = new Meal(mealType);
		newMeal.addWater(water);
		mealList.add(newMeal);
	}

	public float calculateCalories() {
		float calories = 0;
		for (Meal meal : mealList)
			calories += meal.calculateCalories();
		return calories;
	}

	public <T> float calculate(Class<T> myClass){
		float value = 0;
		for (Meal meal : mealList)
			value += meal.calculate(myClass);
		return value;
	}
}
