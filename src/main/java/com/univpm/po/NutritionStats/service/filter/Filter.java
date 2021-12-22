package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;

import com.univpm.po.NutritionStats.model.Diary;

public abstract class Filter {

	protected boolean dateIsBetween(LocalDate date, LocalDate startDate, LocalDate endDate) {
		if ((date.isAfter(startDate) || date.isEqual(startDate))
				&& (date.isBefore(endDate) || date.isEqual(endDate)))
			return true;

		return false;
	}
	
	public abstract void filter (Diary diary);
	
}
