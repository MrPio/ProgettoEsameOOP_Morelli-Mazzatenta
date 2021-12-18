package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;
import java.util.ArrayList;

import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FilterByDate extends Filter {

	public FilterByDate(JSONObject diary) {
		super(diary);
	}

	public JSONObject filter(LocalDate startDate, LocalDate endDate) {

		ArrayList<Day> dayList = ((Diary) diary.get("diary")).getDayList();
		ArrayList<Day> filteredList = new ArrayList<>();
		
		for (Day day : dayList)
			if (dateIsBetween(day.getDate(), startDate, endDate))
				filteredList.add(day);

		filteredData.put("dayList", filteredList);
		return  filteredData;
	}
}
