package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;

import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.model.Diary;

public abstract class Filter {
	
	protected Diary diary;
	
	protected JSONObject filteredData = new JSONObject();
	
	public Filter(Diary diary) {
		this.diary = diary;
	}
	
	public Diary getDiary() {
		return diary;
	}

	protected boolean dateIsBetween(LocalDate date, LocalDate startDate, LocalDate endDate) {
		if ((date.isAfter(startDate) || date.isEqual(startDate))
				&& (date.isBefore(endDate) || date.isEqual(endDate)))
			return true;

		return false;
	}
	
	public abstract void filter ();
	
}
