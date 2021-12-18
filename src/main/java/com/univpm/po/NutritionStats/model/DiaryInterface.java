package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.api.DropboxAPI;
import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.utility.InputOutputImpl;
import com.univpm.po.NutritionStats.utility.SerializationImpl;
import org.json.simple.JSONObject;

public interface DiaryInterface {

    Day findDayById(String dayId);

    void addFood(String dayId, MealType mealType, Food food);

    void addWater(String dayId, MealType mealType, Water water);

    JSONObject toJsonObject();
}
