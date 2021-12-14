package com.univpm.po.NutritionStats.model;

import java.util.ArrayList;

import com.univpm.po.NutritionStats.enums.MealType;

public class Meal {

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

    public int calculateCalories() {
        int calories = 0;
        for (Food food : foodList)
            calories += food.calculateCalories();
        return calories;
    }

    public int calculateWater() {
        int milliLiters = 0;
        for (Water water : waterList)
            milliLiters += water.getVolume();
        return milliLiters;
    }

    public int calculateCarbohydrates() {
        int carbohydrates = 0;
        for (Food food : foodList)
            carbohydrates += food.getCarbohydrates();
        return carbohydrates;
    }

    public int calculateProteins() {
        int proteins = 0;
        for (Food food : foodList)
            proteins += food.getProtein();
        return proteins;
    }

    public int calculateLipids() {
        int lipids = 0;
        for (Food food : foodList)
            lipids += food.getLipids();
        return lipids;
    }
}
