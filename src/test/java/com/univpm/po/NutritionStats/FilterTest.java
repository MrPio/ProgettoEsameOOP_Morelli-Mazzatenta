package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.service.filter.FilterByDate;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class FilterTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void test() throws ParseException {
        LocalDate begin = LocalDate.parse("10/11/2021", Diary.formatter);
        LocalDate end = LocalDate.parse("18/11/2021", Diary.formatter);

        Diary diary = Diary.load("3959de8aeefabfa1385135fa8d03ee21");

		assert diary != null;

		/*FilterByDate f = new FilterByDate(diary.toJsonObject());
        System.out.println(diary.toJsonObject().toJSONString());
        System.out.println(f.filter(begin, end));*/

    }

}