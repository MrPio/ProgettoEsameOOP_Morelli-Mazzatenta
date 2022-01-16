package com.univpm.po.NutritionStats.service.filter;

import java.time.LocalDate;

import com.univpm.po.NutritionStats.model.Diary;

/**
 * Represents a filter that can be one among filter by date, filter by food,
 * filter by meal type, filter by nutrient or not nutrient and filter by water.
 * 
 * @author Davide
 *
 */
public abstract class Filter {

	/**
	 * Checks a date is between the start date and the end date and return true if
	 * is it. If the start and end date are the same and the date you want to check
	 * is exactly that date it return true, otherwise return false.
	 * 
	 * @param date      you want to check
	 * @param startDate start date
	 * @param endDate   end date
	 * @return true if the date you want to check is between start and end date or
	 *         if is exactly them, false in other cases
	 */
	protected boolean dateIsBetween(LocalDate date, LocalDate startDate, LocalDate endDate) {
		return (date.isAfter(startDate) || date.isEqual(startDate))
				&& (date.isBefore(endDate) || date.isEqual(endDate));
	}

	/**
	 * Filter diary given as a parameter depending on which filter is it
	 * 
	 * @param diary to filter
	 */
	public abstract void filter(Diary diary);
}
