package com.univpm.po.NutritionStats.enums;

import com.univpm.po.NutritionStats.service.statistic.Mean;
import com.univpm.po.NutritionStats.service.statistic.Percentage;
import com.univpm.po.NutritionStats.service.statistic.StandardDeviation;
import com.univpm.po.NutritionStats.service.statistic.Statistic;

public enum StatisticType {
	PERCENTAGE(Percentage.class), 
	MEAN(Mean.class), 
	STANDARD_DEVIATION(StandardDeviation.class);

	Class<? extends Statistic> referenceClass;

	StatisticType(Class<? extends Statistic> referenceClass) {
		this.referenceClass = referenceClass;
	}

	public Class<? extends Statistic> getReferenceClass() {
		return referenceClass;
	}
}
