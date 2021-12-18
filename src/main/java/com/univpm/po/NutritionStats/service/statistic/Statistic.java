package com.univpm.po.NutritionStats.service.statistic;

import java.time.LocalDate;

import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.User;

public abstract class Statistic {

	protected Diary diary;

	public Statistic(Diary diary) {
		this.diary = diary;
	}

	protected boolean dateIsBetween(LocalDate date, LocalDate startDate, LocalDate endDate) {
		if ((date.isAfter(startDate) || date.isEqual(startDate))
				&& (date.isBefore(endDate) || date.isEqual(endDate)))
			return true;
		return false;
	}
	
	protected void checkDateException(LocalDate startDate, LocalDate endDate) throws EndDateBeforeStartDateException {
		if (endDate.isBefore(startDate))
			throw new EndDateBeforeStartDateException(startDate,endDate);
	}
}