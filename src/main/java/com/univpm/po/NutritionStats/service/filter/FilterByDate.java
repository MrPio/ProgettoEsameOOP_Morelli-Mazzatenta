package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;
import java.util.ArrayList;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FilterByDate extends Filter {

	private LocalDate startDate;
	private LocalDate endDate;
	
	public FilterByDate(Diary diary, LocalDate startDate, LocalDate endDate) {
		super(diary);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public void filter() {

		for (Day day : diary.getDayList())
			if (!(dateIsBetween(day.getDate(), startDate, endDate)))
				diary.getDayList().remove(day);
	}
}
