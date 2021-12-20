package com.univpm.po.NutritionStats.exception;

import java.time.LocalDate;

public class EndDateBeforeStartDateException extends Exception {
	final static String BASE_MESSAGE = "Error, dates entered are wrong.";
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	public EndDateBeforeStartDateException(LocalDate startDate, LocalDate endDate) {
		super(BASE_MESSAGE+" "+endDate+" is before "+startDate);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}
	
}
