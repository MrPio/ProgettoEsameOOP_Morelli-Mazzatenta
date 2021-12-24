package com.univpm.po.NutritionStats.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.enums.MealType;

public class Day implements Serializable {
    private LocalDate date;
    private ArrayList<Meal> mealList;
    private ArrayList<Water> waterList;
    private Map<AllNutrientNonNutrient, Float> sumValues = new HashMap<>() {
    };

    public Day(LocalDate date) {
        this.date = date;
        this.mealList = new ArrayList<>();
        this.waterList = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public ArrayList<Water> getWaterList() {
        return waterList;
    }

    public String calculateDayId() {
        return Diary.formatter.format(date);
    }

    public ArrayList<Meal> getMealList() {
        return mealList;
    }

    public Map<AllNutrientNonNutrient, Float> getSumValues() {
        for (var nutrient : AllNutrientNonNutrient.values())
            if (calculate(nutrient.getReferenceClass()) > 0.0000001f &&
                    nutrient != AllNutrientNonNutrient.SUGAR &&
                    nutrient != AllNutrientNonNutrient.SATURATED)
                sumValues.put(nutrient, calculate(nutrient.getReferenceClass()));
        return sumValues;
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

    public void addWater(Water water) {
        waterList.add(water);
    }

    public float getTotalCalories() {
        float calories = 0;
        for (Meal meal : mealList)
            calories += meal.getTotalCalories();
        return calories;
    }

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
