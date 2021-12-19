package com.univpm.po.NutritionStats;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.service.filter.FilterByFood;

public class FilterByFoodTest {

	@BeforeEach
    void setUp() {
    	// dd46a756faad4727fb679320751f6dea
    }

    @Test
    void test() throws ParseException {
        
    	String food = "lasagna";

        Diary diary = Diary.load("dd46a756faad4727fb679320751f6dea");

		assert diary != null;
		FilterByFood f = new FilterByFood(diary.toJsonObject());
        System.out.print(diary.toJsonObject());
        System.out.print(f.filter(food));

    }
}
