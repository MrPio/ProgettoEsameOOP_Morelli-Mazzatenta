package com.univpm.po.NutritionStats.exception;

import com.univpm.po.NutritionStats.enums.Api;

public class ChompLimitOvercameException extends Exception{
    final static String BASE_MESSAGE="Sorry, but you overcame the limit of requests, please try again later. More details here:";

    private String api;
    private String limit;

    public ChompLimitOvercameException(int limit) {
        super(BASE_MESSAGE);
        this.api=Api.CHOMP.name();
        this.limit = limit+" requests";
    }

    public String getApi() {
        return api;
    }

    public String getLimit() {
        return limit;
    }
}
