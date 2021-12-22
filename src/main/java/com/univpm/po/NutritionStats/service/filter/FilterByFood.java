package com.univpm.po.NutritionStats.service.filter;


import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.Meal;

public class FilterByFood extends Filter {

    private String foodName;

    public FilterByFood(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public void filter(Diary diary) {
        for (Day day : diary.getDayList())
            for (Meal meal : day.getMealList())
                meal.getFoodList().removeIf(food -> !(food.getName().equals(foodName)));

            /*    for (Food food : meal.getFoodList())
                    if (!(food.getName().equals(foodName)))
                        meal.getFoodList().remove(food);*/
    }
}
