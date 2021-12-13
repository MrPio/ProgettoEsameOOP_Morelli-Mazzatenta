package com.univpm.po.NutritionStats.model.meal;
import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.Water;

import java.util.ArrayList;

public abstract class Meal {
	
	protected ArrayList<Food> foodList = new ArrayList<Food>();
	protected ArrayList<Water> waterList = new ArrayList<Water>();
	
	public abstract void addFood();
	public abstract void addWater();
	public abstract int calculateMealCalories();
	public abstract int calculateTotalWater();

}
