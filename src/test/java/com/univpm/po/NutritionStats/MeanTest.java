package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.service.statistic.Mean;
import com.univpm.po.NutritionStats.service.statistic.Percentage;
import com.univpm.po.NutritionStats.service.statistic.StandardDeviatiton;
import org.junit.jupiter.api.Test;

class MeanTest {
	final String email = "email@example.com";

	@Test
	void testWithDebugger() {

		Diary diary = Diary.load("3959de8aeefabfa1385135fa8d03ee21");
		Mean mean = new Mean();
		StandardDeviatiton sd = new StandardDeviatiton();
		Percentage percentage = new Percentage();
		mean.calculateStatistic(diary);
		sd.calculateStatistic(diary);
		percentage.calculateStatistic(diary);
		System.out.println(mean.getStatsValues());
		System.out.println(sd.getStatsValues());
		System.out.println(percentage.getStatsValues());
	}

}
