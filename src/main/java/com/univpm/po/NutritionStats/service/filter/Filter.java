package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;

import org.json.simple.JSONObject;

public abstract class Filter {
	
	protected JSONObject diary;
	
	public Filter(JSONObject diary) {
		this.diary = diary;
	}
	
	protected boolean dateIsBetween(LocalDate date, LocalDate startDate, LocalDate endDate) {
		if ((date.isAfter(startDate) || date.isEqual(startDate))
				&& (date.isBefore(endDate) || date.isEqual(endDate)))
			return true;

		return false;
	}
	
}
