package com.univpm.po.NutritionStats;

import java.time.LocalDate;
import java.util.Map;

import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.nutrient.MacroNutrient;
import com.univpm.po.NutritionStats.service.statistic.StandardDeviatiton;
import org.junit.jupiter.api.Test;

import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.service.statistic.Mean;

class MeanTest {
	final String email = "email@example.com";

	@Test
	void testWithDebugger() throws EndDateBeforeStartDateException {

		LocalDate begin = LocalDate.parse("17/12/2021",Diary.formatter);
		LocalDate end = LocalDate.parse("18/12/2021",Diary.formatter);

		Diary diary = Diary.load("3959de8aeefabfa1385135fa8d03ee21");
		Mean mean = new Mean(diary);
		StandardDeviatiton sd=new StandardDeviatiton(diary);
		System.out.println(mean.allNutrientMean(begin, end));
		System.out.println(sd.calculateStandardDeviation(begin, end));

		for (Map.Entry<Class<?>, Float> entry : mean.getMeanList().entrySet())
			System.out.println(((Class<? extends MacroNutrient>) entry.getKey()).getFields());
	}

}
