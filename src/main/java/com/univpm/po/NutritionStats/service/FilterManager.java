package com.univpm.po.NutritionStats.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univpm.po.NutritionStats.controller.Controller;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Water;
import com.univpm.po.NutritionStats.service.filter.*;
import com.univpm.po.NutritionStats.service.statistic.Statistic;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * {@code FilterManager} class is used to handle the request body used by <b>/stats</b> and <b>/filter</b> endpoints.
 *
 * <p>The constructor checks if the entered values are valid, otherwise it stores an instance of a class
 * which extends {@link Exception} inside the instance variable exception.
 * Then the {@link Controller} checks if this variable is null or not.
 *
 * @author Valerio Morelli
 */
public class FilterManager {

    /**
     * An {@link ArrayList} containing all the filter found inside the request body. Each filter is a subclass of
     * {@link Statistic} which can be assigned to an instance of {@link Statistic} thanks to the LateBinding.
     */
    private final ArrayList<Filter> filtersList = new ArrayList<>();

    /**
     * An instance of {@link Exception} containing the null value only if the constructor does not find any
     * problem with the provided data.
     */
    private Exception exception = null;

    /**
     * <b>The constructor in charge of checking if the provided values are valid.</b>
     *
     * @param startDate     the left period range extremity.
     * @param endDate       the right period range extremity.
     * @param mealType      the {@link MealType type of meals} in which the client is interested.
     * @param foodName      the name of food to leave inside the diary.
     * @param water         if the client wants to filter the information on his {@link Water water} assumption.
     * @param nutrient_name a list of nutrients to leave inside the diary.
     */
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
