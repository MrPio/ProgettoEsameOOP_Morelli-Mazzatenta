package com.univpm.po.NutritionStats.service.filter;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.Meal;
import com.univpm.po.NutritionStats.model.nutrient.NotNutrient;
import com.univpm.po.NutritionStats.model.nutrient.Nutrient;

public class FilterByNutrientNotNutrient extends Filter {
	
    private String nutrientNotNutrientName;

    public FilterByNutrientNotNutrient(String nutrientNotNutrientName) {
        this.nutrientNotNutrientName = nutrientNotNutrientName;
    }
    
	@Override
	public void filter(Diary diary) {
		 for (Day day : diary.getDayList())
			 for (Meal meal : day.getMealList())
				 for (Food food : meal.getFoodList()) {
	            	for (Nutrient nutrient : food.getNutrientList()) 
	            		if (!(nutrient.getClass().getSimpleName().toLowerCase().equals(nutrientNotNutrientName)))
	            			food.getNutrientList().remove(nutrient);
	            		for (NotNutrient notNutrient : food.getNotNutrientList())
		            		if (!(notNutrient.getClass().getSimpleName().toLowerCase().equals(nutrientNotNutrientName)))
		            			food.getNotNutrientList().remove(notNutrient);
	            	}
	}
}
