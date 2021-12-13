package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.Water;

import java.util.ArrayList;

public class Meal {
    public enum MealType {
        BREAKFAST(0.20f),
        LAUNCH(0.40f),
        SNACK(0.10f),
        DINNER(0.30f);

        float dailyNeed;

        MealType(float dailyNeed) {
            this.dailyNeed = dailyNeed;
        }
    }

    private MealType mealType;
    private ArrayList<Food> foodList;
    private ArrayList<Water> waterList;

    public Meal(MealType mealType) {
        this.mealType = mealType;
        this.foodList = new ArrayList<>();
        this.waterList = new ArrayList<>();
    }

    public Meal(MealType mealType, ArrayList<Food> foodList, ArrayList<Water> waterList) {
        this.mealType = mealType;
        this.foodList = foodList;
        this.waterList = waterList;
    }

    public MealType getMealType() {
        return mealType;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public ArrayList<Water> getWaterList() {
        return waterList;
    }

    public void addFood(Food food) {
        foodList.add(food);
    }

    public void addWater(Water water) {
        waterList.add(water);
    }

    public int calculateMealCalories() {
        int calories = 0;
        for (Food food : foodList)
            calories += food.calculateFoodCalories();
        return calories;
    }

    public int calculateMealWater() {
        int milliLiters = 0;
        for (Water water : waterList)
            milliLiters += water.getValue().value;
        return milliLiters;
    }

    public int calculateMealCarbohydrates() {
        int carbohydrates = 0;
        for (Food food : foodList)
            carbohydrates += food.getCarbohydrates();
        return carbohydrates;
    }

    public int calculateMealProteins() {
        int proteins = 0;
        for (Food food : foodList)
            proteins += food.getProtein();
        return proteins;
    }

    public int calculateMealLipids() {
        int lipids = 0;
        for (Food food : foodList)
            lipids += food.getLipids();
        return lipids;
    }
}
