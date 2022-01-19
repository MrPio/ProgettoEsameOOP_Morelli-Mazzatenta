package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.enums.MealType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a meal in a day which can be breakfast, lunch, snack or dinner
 * with its food list.
 *
 * @author Davide Mazzatenta
 */
public class Meal implements Serializable {

    private MealType mealType;
    private ArrayList<Food> foodList;

    /**
     * Class first constructor that instantiates a meal with a type and a food list
     *
     * @param mealType of the meal: breakfast, lunch, snack or dinner
     */
    public Meal(MealType mealType) {
        this.mealType = mealType;
        this.foodList = new ArrayList<>();
    }

    /**
     * Class second constructor that instantiates a meal, a food list and a water
     * list
     *
     * @param mealType  of the meal: breakfast, lunch, snack or dinner
     * @param foodList  of the meal that contains one or more foods
     * @param waterList of the meal that contains water
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
     * Add food to the food list
     *
     * @param food to add
     */
    public void addFood(Food food) {
        foodList.add(food);
    }

    /**
     * Calculate total calories of a meal through foods. A food is made of nutrients
     * and not nutrients. Total calories are calculated with nutrients, specifically
     * with macronutrients that are carbohydrates, proteins or lipids.
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
     * the method in Food Class that calculates the quantity of nutrients and not
     * nutrients in a food.
     *
     * @param <T>     means that the method will be dealing with generic type
     * @param myClass is a class object of a specific class type <T>. It can be a
     *                String, an Integer or a Object
     * @return nutrient or not nutrient quantity in a meal
     */
    public <T> float calculate(Class<T> myClass) {
        float value = 0;
        for (Food food : foodList)
            value += food.calculate(myClass);
        return value;
    }
}
