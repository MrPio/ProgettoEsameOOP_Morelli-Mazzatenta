package com.univpm.po.NutritionStats.exception;

import java.time.LocalDate;

/**
 * The {@code EndDateBeforeStartDateException} class is an {@link Exception} thrown when a wrong range of date
 * is given by the client; in specific when the left extreme of the range is greater than the right one.
 *
 * @author Davide Mazzatenta
 */
public class EndDateBeforeStartDateException extends Exception {
    final static String BASE_MESSAGE = "Error, dates entered are wrong.";
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * The constructor of the exception.
     *
     * @param startDate the left extreme of the date range.
     * @param endDate   the right extreme of the date range.
     */
    public EndDateBeforeStartDateException(LocalDate startDate, LocalDate endDate) {
        super(BASE_MESSAGE + " " + endDate + " is before " + startDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the left extreme of the date range.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @return the right extreme of the date range.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

}
