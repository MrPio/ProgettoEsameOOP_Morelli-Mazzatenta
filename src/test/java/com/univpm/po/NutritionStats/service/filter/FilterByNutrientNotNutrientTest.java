package com.univpm.po.NutritionStats.service.filter;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Diary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilterByNutrientNotNutrientTest {

    @Test
    void filter() {
        Diary diary = Diary.load("3959de8aeefabfa1385135fa8d03ee21");
        new FilterByNutrientNotNutrient(new AllNutrientNonNutrient[]{AllNutrientNonNutrient.PROTEIN}).filter(diary);
        assert true;
    }
}