package com.univpm.po.NutritionStats.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.service.filter.*;


public class FilterManager {

    private final ArrayList<Filter> filtersList = new ArrayList<>();
    private Exception exception = null;

    public FilterManager(
            @JsonProperty("start_date") String startDate,
            @JsonProperty("end_date") String endDate,
            @JsonProperty("meal_type") MealType mealType,
            @JsonProperty("food_name") String foodName,
            @JsonProperty("water") Boolean water,
            @JsonProperty("nutrient_name") AllNutrientNonNutrient[] nutrient_name) {

        if (startDate != null && endDate != null) {
            LocalDate start = Diary.stringToLocalDate(startDate);
            LocalDate end = Diary.stringToLocalDate(endDate);

            if (end.isBefore(start)) {
                exception = new EndDateBeforeStartDateException(start, end);
                return;
            }

            filtersList.add(new FilterByDate(start, end));
        }

        if (mealType != null)
            filtersList.add(new FilterByMealType(mealType));
        if (foodName != null)
            filtersList.add(new FilterByFood(foodName));
        if (water != null && !water)
            filtersList.add(new FilterWater());
        if (nutrient_name != null)
            filtersList.add(new FilterByNutrientNotNutrient(nutrient_name));
    }

    public ArrayList<Filter> getFiltersList() {
        return filtersList;
    }

    public Exception getException() {
        return exception;
    }
}
