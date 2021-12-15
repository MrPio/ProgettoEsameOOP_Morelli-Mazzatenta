package com.univpm.po.NutritionStats.service;

import com.univpm.po.NutritionStats.model.Diary;

public abstract class Statistic {
	
	protected Diary diary;

	public Statistic(Diary diary) {
		this.diary = diary;
	}
	
}
