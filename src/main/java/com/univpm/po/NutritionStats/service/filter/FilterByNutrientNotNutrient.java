package com.univpm.po.NutritionStats.service.filter;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.Meal;
import com.univpm.po.NutritionStats.model.nutrient.NotNutrient;
import com.univpm.po.NutritionStats.model.nutrient.Nutrient;

import java.util.ArrayList;

public class FilterByNutrientNotNutrient extends Filter {

    private AllNutrientNonNutrient[] names;

    public FilterByNutrientNotNutrient(AllNutrientNonNutrient[] names) {
        this.names = names;
    }

    @Override
    public void filter(Diary diary) {
        for (Day day : diary.getDayList())
            for (Meal meal : day.getMealList())
                for (Food food : meal.getFoodList()) {
                    food.getNutrientList().removeIf(nutrient -> !contains(nutrient));
                    food.getNotNutrientList().removeIf(notNutrient -> !contains(notNutrient));
                }
    }

    private boolean contains(Object element) {
        for (var name : names)
            if (name.getReferenceClass() == element.getClass())
                return true;
        return false;
    }
}
