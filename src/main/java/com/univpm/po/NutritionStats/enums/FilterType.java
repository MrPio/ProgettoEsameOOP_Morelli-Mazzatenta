package com.univpm.po.NutritionStats.enums;

import com.univpm.po.NutritionStats.service.filter.Filter;
import com.univpm.po.NutritionStats.service.filter.FilterByDate;
import com.univpm.po.NutritionStats.service.filter.FilterByFood;
import com.univpm.po.NutritionStats.service.filter.FilterByMealType;

public enum FilterType {

	FILTER_BY_DATE(FilterByDate.class), 
	FILTER_BY_MEAL_TYPE(FilterByMealType.class), 
	FILTER_BY_FOOD(FilterByFood.class);

	Class<? extends Filter> referenceClass;

	FilterType(Class<? extends Filter> referenceClass) {
		this.referenceClass = referenceClass;
	}

	public Class<? extends Filter> getReferenceClass() {
		return referenceClass;
	}
}
