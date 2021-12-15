package com.univpm.po.NutritionStats.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.model.nutrient.NotNutrient;

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

    public int calculateWater() {
        int milliLiters = 0;
        for (Water water : waterList)
            milliLiters += water.getVolume();
        for (Food food : foodList)
        	milliLiters += food.getWaterFromFood();
        return milliLiters;
    }

    public int calculateCarbohydrates() {
        int carbohydrates = 0;
        for (Food food : foodList)
            carbohydrates += food.getCarbohydrates();
        return carbohydrates;
    }

    public float calculateProteins() {
    	float proteins = 0;
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
   
    public int calculateFiber() {
        int fiber = 0;
        for (Food food : foodList)
            fiber += food.getFiber();
        return fiber;
    }
    
    public float calculateVitaminA() {
    	float vitaminA = 0;
    	for (Food food : foodList)
    		vitaminA += food.getVitaminA();
    	return vitaminA;
    }
    
    public float calculateVitaminC() {
    	float vitaminC = 0;
    	for (Food food : foodList)
    		vitaminC += food.getVitaminA();
    	return vitaminC;
    }
    
    public float calculateSodium() {
    	float sodium = 0;
    	for (Food food : foodList)
    		sodium += food.getSodium();
    	for (Water water : waterList)
    		sodium += water.getSodium();
    	return sodium;
    }
    
    public float calculateCalcium() {
    	float calcium = 0;
    	for (Food food : foodList)
    		calcium += food.getCalcium();
    	for (Water water : waterList)
    		calcium += water.getCalcium();
    	return calcium;
    }
    
    public float calculatePotassium() {
    	float potassium = 0;
    	for (Food food : foodList)
    		potassium += food.getPotassium();
    	return potassium;
    }
    
    public float calculateIron() {
    	float iron = 0;
    	for (Food food : foodList)
    		iron += food.getIron();
    	return iron;
    }
    
    
}
