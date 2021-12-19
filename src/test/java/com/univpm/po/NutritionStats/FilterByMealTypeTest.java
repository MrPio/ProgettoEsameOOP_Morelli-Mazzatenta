package com.univpm.po.NutritionStats;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.service.filter.FilterByDate;
import com.univpm.po.NutritionStats.service.filter.FilterByMealType;

class FilterByMealTypeTest {

    @BeforeEach
    void setUp() {
    	// dd46a756faad4727fb679320751f6dea
    }

    @Test
    void test() throws ParseException {
        //MealType typeOfMeal = MealType.BREAKFAST;

        Diary diary = Diary.load("dd46a756faad4727fb679320751f6dea");

		assert diary != null;
		FilterByMealType f = new FilterByMealType(diary.toJsonObject());
        System.out.print(diary.toJsonObject());
        System.out.print(f.filter(MealType.LUNCH).toJSONString());

    }
}
