package com.univpm.po.NutritionStats.service.filter;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Meal;

public class FilterWater extends Filter {
    @Override
    public void filter(Diary diary) {
        for(Day day:diary.getDayList())
            day.getWaterList().clear();
    }
}
