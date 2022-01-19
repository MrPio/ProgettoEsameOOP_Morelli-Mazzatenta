package com.univpm.po.NutritionStats.service.filter;

import com.univpm.po.NutritionStats.model.Diary;

import java.time.LocalDate;

/**
 * Represents a specific type of filter which is filter by date. Filter by date
 * means that you remove days on diary that aren't in the range between start
 * date and end date.
 *
 * @author Davide
 */
public class FilterByDate extends Filter {

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Class constructor that instantiates a filter by date with start date and end
     * date.
     *
     * @param startDate begin of the range that will be filtered
     * @param endDate   end of the range that will be filtered
     */
    public FilterByDate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Remove days on diary day list and on the map of weights that aren't between
     * start and end date so you get a filtered diary with the range of days you
     * wanted to filter.
     */
    @Override
    public void filter(Diary diary) {
        diary.getDayList().removeIf(day -> !(dateIsBetween(day.getDate(), startDate, endDate)));
        diary.getUser().getWeight().entrySet().removeIf(entry -> !(dateIsBetween(entry.getKey(), startDate, endDate)));
    }
}
