package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Meal;

public class FilterByMealType extends Filter {

    private MealType mealType;

    public FilterByMealType(MealType mealType) {
        this.mealType = mealType;
    }

    @Override
    public void filter(Diary diary) {
        for (Day day : diary.getDayList())
            day.getMealList().removeIf(meal -> !(mealType == meal.getMealType()));
    }
}
