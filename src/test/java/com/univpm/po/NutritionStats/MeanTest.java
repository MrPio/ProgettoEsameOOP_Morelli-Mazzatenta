package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.service.statistic.Mean;
import com.univpm.po.NutritionStats.service.statistic.Percentage;
import com.univpm.po.NutritionStats.service.statistic.StandardDeviatiton;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class MeanTest {
	final String email = "email@example.com";

	@Test
	void testWithDebugger() throws EndDateBeforeStartDateException {

		LocalDate begin = LocalDate.parse("15/11/2021", Diary.formatter);
		LocalDate end = LocalDate.parse("18/11/2021", Diary.formatter);

		Diary diary = Diary.load("3959de8aeefabfa1385135fa8d03ee21");
		Mean mean = new Mean(diary);
		StandardDeviatiton sd=new StandardDeviatiton(diary);
		Percentage percentage=new Percentage(diary);
/*		System.out.println(mean.calculateStatistic(begin, end));
		System.out.println(sd.calculateStatistic(begin, end));
		System.out.println(percentage.calculateStatistic(begin, end));*/
	}

}
