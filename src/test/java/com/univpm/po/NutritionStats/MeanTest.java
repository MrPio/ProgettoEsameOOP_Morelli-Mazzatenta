package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.service.statistic.Mean;
import com.univpm.po.NutritionStats.service.statistic.Percentage;
import com.univpm.po.NutritionStats.service.statistic.StandardDeviation;
import org.junit.jupiter.api.Test;

class MeanTest {

	/**
	 * Try to load and calculate statistics on a sample diary.
	 * @see com.univpm.po.NutritionStats.model.Diary
	 */
	@Test
	void calculateStatistics() {
		Diary diary = Diary.load("3959de8aeefabfa1385135fa8d03ee21");
		Mean mean = new Mean();
		StandardDeviation sd = new StandardDeviation();
		Percentage percentage = new Percentage();
		mean.calculateStatistic(diary);
		sd.calculateStatistic(diary);
		percentage.calculateStatistic(diary);
		System.out.println(mean.getStatsValues());
		System.out.println(sd.getStatsValues());
		System.out.println(percentage.getStatsValues());
	}

}
