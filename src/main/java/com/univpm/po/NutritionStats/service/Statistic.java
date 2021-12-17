package com.univpm.po.NutritionStats.service;

import java.time.LocalDate;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;

public abstract class Statistic {
	
	protected Diary diary;

	public Statistic(Diary diary) {
		this.diary = diary;
	}
	
	public boolean isBetween(Day day, LocalDate startDate, LocalDate endDate) {
		if((day.getDate().isAfter(startDate) || day.getDate().isEqual(startDate)) 
				&& (day.getDate().isBefore(endDate) || day.getDate().isEqual(endDate)))
			return true;
		
		return false;
		
	}
	
}
