package com.univpm.po.NutritionStats.enums;

import com.univpm.po.NutritionStats.service.statistic.*;

public enum StatisticType {
	PERCENTAGE(Percentage.class), 
	MEAN(Mean.class), 
	STANDARD_DEVIATION(StandardDeviation.class),
	CORRELATION(Correlation.class);

	Class<? extends Statistic> referenceClass;

	StatisticType(Class<? extends Statistic> referenceClass) {
		this.referenceClass = referenceClass;
	}

	public Class<? extends Statistic> getReferenceClass() {
		return referenceClass;
	}
}
