package com.univpm.po.NutritionStats.service;

import java.time.LocalDate;

import org.json.simple.JSONObject;

import com.univpm.po.NutritionStats.model.Diary;

public class Mean extends Statistic {

	public Mean(Diary diary) {
		super(diary);
	}

	public JSONObject Mean (LocalDate startDate, LocalDate endDate) {
		float[] means = new float[6];
		int carbohydrates, lipid, protein;
		float vitaminA, vitaminC, 
	}
	
}
