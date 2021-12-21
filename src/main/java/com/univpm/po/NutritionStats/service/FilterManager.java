package com.univpm.po.NutritionStats.service;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univpm.po.NutritionStats.enums.FilterType;
import com.univpm.po.NutritionStats.enums.MealType;


public class FilterManager {
	
	ArrayList<FilterType> filteredList = new ArrayList<FilterType>();
	private String startDate;
	private String endDate;
	private MealType mealType;
	private String foodName;

	public FilterManager(
			@JsonProperty("startDate") String startDate, 
			@JsonProperty("endDate") String endDate,
			@JsonProperty("mealType") MealType mealType,
			@JsonProperty("food") String foodName
			) {
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.mealType = mealType;
		this.foodName = foodName;
		
		if (startDate != null && endDate != null) {
			filteredList.add(FilterType.FILTER_BY_DATE);
		}
		
		if (mealType != null) {
			filteredList.add(FilterType.FILTER_BY_MEAL_TYPE);
		}
		if (foodName != null) {
			filteredList.add(FilterType.FILTER_BY_FOOD);
		}
	}

	public ArrayList<FilterType> getFilteredList() {
		return filteredList;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public MealType getMealType() {
		return mealType;
	}

	public String getFoodName() {
		return foodName;
	}
	
	
	
}
