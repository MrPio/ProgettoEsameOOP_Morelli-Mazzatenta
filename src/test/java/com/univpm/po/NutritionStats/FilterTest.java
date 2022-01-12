package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.service.filter.FilterByDate;
import com.univpm.po.NutritionStats.service.filter.FilterByNutrientNotNutrient;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class FilterTest {

    /**
     * Try to load ad filter sample diary by date.
     * @see com.univpm.po.NutritionStats.model.Diary
     */
    @Test
    void filterByDate() {
        LocalDate begin = LocalDate.parse("10/11/2021", Diary.formatter);
        LocalDate end = LocalDate.parse("18/12/2021", Diary.formatter);

        Diary diary = Diary.load("3959de8aeefabfa1385135fa8d03ee21");

		assert diary != null;

		FilterByDate filter = new FilterByDate(begin,end);
        filter.filter(diary);
        System.out.println("Found "+diary.getDayList().size()+" compatible dates.");

    }

    /**
     * Try to load and filter sample diary by nutrient name.
     * @see com.univpm.po.NutritionStats.model.Diary
     */
    @Test
    void filterByNutrientNotNutrient() {
        Diary diary = Diary.load("3959de8aeefabfa1385135fa8d03ee21");
        assert diary != null;
        new FilterByNutrientNotNutrient(new AllNutrientNonNutrient[]{AllNutrientNonNutrient.PROTEIN})
                .filter(diary);
    }

}
