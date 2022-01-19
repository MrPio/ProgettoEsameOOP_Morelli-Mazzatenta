package com.univpm.po.NutritionStats.service.filter;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;

/**
 * Represents a specific type of filter which is filter by water.
 *
 * @author Davide
 */
public class FilterWater extends Filter {

    /**
     * Clears all the water list.
     */
    @Override
    public void filter(Diary diary) {
        for (Day day : diary.getDayList())
            day.getWaterList().clear();
    }
}
