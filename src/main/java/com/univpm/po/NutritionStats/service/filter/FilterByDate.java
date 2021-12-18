package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FilterByDate extends Filter {

	public FilterByDate(JSONObject diary) {
		super(diary);
	}

	public JSONObject filter(LocalDate startDate, LocalDate endDate) {

		JSONArray diaryByDate = (JSONArray) diary.get("dayList");
		JSONArray filteredList = new JSONArray();
		
		for (Object day : diaryByDate) {
			LocalDate date = (LocalDate) ((JSONObject) day).get("date");
			if (dateIsBetween(date, startDate, endDate)) {
				filteredList.add(day);
			}
			
		}
		return (JSONObject) filteredData.put("dayList", filteredList);
	}
}
