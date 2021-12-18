package com.univpm.po.NutritionStats.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.model.nutrient.NotNutrient;
import com.univpm.po.NutritionStats.model.nutrient.WaterFromFood;

public class Meal implements Serializable {

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
            calories += food.getCalories();
        return calories;
    }

    public <T> float calculate(Class<T> myClass){
        float value = 0;
        for (Food food : foodList)
            value += food.calculate(myClass);

        if(myClass==Water.class)
            for (Water water : waterList)
                value += water.getVolume();
        return value;
    }
}
