package com.univpm.po.NutritionStats.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class Day {
    private LocalDate date;
    private ArrayList<Meal> mealList;

    public Day(LocalDate date) {
        this.date = date;
        this.mealList = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public ArrayList<Meal> getMealList() {
        return mealList;
    }

    public void addFood(Meal.MealType mealType,Food food) {
        //if I already have a meal with the same type I add the food there
        for (Meal meal : mealList)
            if (meal.getMealType() == mealType) {
                meal.getFoodList().add(food);
                return;
            }
        //otherwise, I create a new meal
        Meal newMeal = new Meal(mealType);
        newMeal.getFoodList().add(food);
        mealList.add(newMeal);
    }

    public int calculateDayCalories() {
        int calories = 0;
        for (Meal meal : mealList)
            calories += meal.calculateMealCalories();
        return calories;
    }

    public int calculateDayWater() {
        int milliLiters = 0;
        for (Meal meal : mealList)
            milliLiters += meal.calculateMealWater();
        return milliLiters;
    }

    public int calculateDayCarbohydrates() {
        int carbohydrates = 0;
        for (Meal meal : mealList)
            carbohydrates += meal.calculateMealCarbohydrates();
        return carbohydrates;
    }

    public int calculateDayProteins() {
        int proteins = 0;
        for (Meal meal : mealList)
            proteins += meal.calculateMealProteins();
        return proteins;
    }

    public int calculateDayLipids() {
        int lipids = 0;
        for (Meal meal : mealList)
            lipids += meal.calculateMealLipids();
        return lipids;
    }
}
