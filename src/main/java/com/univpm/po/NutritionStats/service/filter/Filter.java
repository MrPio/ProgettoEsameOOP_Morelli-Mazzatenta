package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;

import com.univpm.po.NutritionStats.model.Diary;

public abstract class Filter {

	protected boolean dateIsBetween(LocalDate date, LocalDate startDate, LocalDate endDate) {
		return (date.isAfter(startDate) || date.isEqual(startDate))
				&& (date.isBefore(endDate) || date.isEqual(endDate));
	}
	
	public abstract void filter (Diary diary);
}
