package com.univpm.po.NutritionStats.exception;

import com.univpm.po.NutritionStats.enums.Api;


/**
 * The {@code ChompLimitOvercameException} class is an {@link Exception} thrown when too many requests
 * on chomp api have been done. This is a form of prudence to avoid high charge.
 * A counter is increased each request and stored remotely; when his value overcome the prefixed limit
 * an instance of this class in thrown.
 * @author Valerio Morelli
 */
public class ChompLimitOvercameException extends Exception{
    final static String BASE_MESSAGE="Sorry, but you overcame the limit of requests, please try again later. More details here:";

    private final String api;
    private final String limit;

    /**
     * The constructor of the exception.
     * @param limit the value of prefixed limit which has been overcome.
     */
    public ChompLimitOvercameException(int limit) {
        super(BASE_MESSAGE);
        this.api=Api.CHOMP.name();
        this.limit = limit+" requests";
    }

    /**
     * @return the name of the api where this exception occurred.
     */
    public String getApi() {
        return api;
    }

    /**
     * @return the value of prefixed limit which has been overcome.
     */
    public String getLimit() {
        return limit;
    }
}
