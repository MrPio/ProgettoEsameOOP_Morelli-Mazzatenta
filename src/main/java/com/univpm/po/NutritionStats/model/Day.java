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
    
    public String getDayId() {
    	return Diary.formatter.format(date);
	}

	public ArrayList<Meal> getMealList() {
        return mealList;
    }

    public void addFood(MealType mealType,Food food) {
        for (Meal meal : mealList)
            if (meal.getMealType().equals(mealType)) {
                meal.addFood(food);
                return;
            }
        Meal newMeal = new Meal(mealType);
        newMeal.addFood(food);
        mealList.add(newMeal);
    }
    
    public void addWater(MealType mealType,Water water) {
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

    public float calculateWater() {
        float milliLiters = 0;
        for (Meal meal : mealList)
            milliLiters += meal.calculateWater();
        return milliLiters;
    }

    public float calculateCarbohydrates() {
        float carbohydrates = 0;
        for (Meal meal : mealList)
            carbohydrates += meal.calculateCarbohydrates();
        return carbohydrates;
    }

    public float calculateProteins() {
        float proteins = 0;
        for (Meal meal : mealList)
            proteins += meal.calculateProteins();
        return proteins;
    }

    public float calculateLipids() {
        float lipids = 0;
        for (Meal meal : mealList)
            lipids += meal.calculateLipids();
        return lipids;
    }
    
    public float calculateFiber() {
        float fiber = 0;
        for (Meal meal : mealList)
            fiber += meal.calculateFiber();
        return fiber;
    }

    public float calculateVitaminA() {
        float vitaminA = 0;
        for (Meal meal : mealList)
        	vitaminA += meal.calculateVitaminA();
        return vitaminA;
    }
    
    public float calculateVitaminC() {
        float vitaminC = 0;
        for (Meal meal : mealList)
        	vitaminC += meal.calculateVitaminC();
        return vitaminC;
    }
    
    public float calculateSodium() {
        float sodium = 0;
        for (Meal meal : mealList)
        	sodium += meal.calculateSodium();
        return sodium;
    }
    
    public float calculateCalcium() {
        float calcium = 0;
        for (Meal meal : mealList)
        	calcium += meal.calculateCalcium();
        return calcium;
    }
    
    public float calculatePotassium() {
        float potassium = 0;
        for (Meal meal : mealList)
        	potassium += meal.calculatePotassium();
        return potassium;
    }
    
    public float calculateIron() {
        float iron = 0;
        for (Meal meal : mealList)
        	iron += meal.calculateIron();
        return iron;
    }
}
