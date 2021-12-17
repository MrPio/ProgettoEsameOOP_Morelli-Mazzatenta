package com.univpm.po.NutritionStats;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.service.Mean;

class MeanTest {

	final String email = "email@example.com";

	@Test
	void testWithDebugger() throws EndDateBeforeStartDateException {

		LocalDate begin = LocalDate.now();
		LocalDate end = LocalDate.now();

		Diary diary = Diary.load("3959de8aeefabfa1385135fa8d03ee21");
		Mean mean = new Mean(diary, null);
		//mean.allNutrientMean(begin, end);

	}

}
