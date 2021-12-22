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

    ArrayList<Filter> filtersList = new ArrayList<>();

    public FilterManager(
            @JsonProperty("start_date") String startDate,
            @JsonProperty("end_date") String endDate,
            @JsonProperty("meal_type") MealType mealType,
            @JsonProperty("food_name") String foodName,
            @JsonProperty("water") Boolean water,
            @JsonProperty("nutrient_name") AllNutrientNonNutrient[] nutrient_name
    ) throws EndDateBeforeStartDateException {

        if (startDate != null && endDate != null) {
            LocalDate start = LocalDate.parse(startDate, Diary.formatter);
            LocalDate end = LocalDate.parse(endDate, Diary.formatter);
            
            if (end.isBefore(start))
            	throw new EndDateBeforeStartDateException(start, end);
            	
            filtersList.add(new FilterByDate(start, end));   
        }

        if (mealType != null)
            filtersList.add(new FilterByMealType(mealType));
        if (foodName != null)
            filtersList.add(new FilterByFood(foodName));
        if(!water)
            filtersList.add(new FilterWater());
        if (nutrient_name != null)
            filtersList.add(new FilterByNutrientNotNutrient(nutrient_name));
    }

    public ArrayList<Filter> getFiltersList() {
        return filtersList;
    }
	
}
