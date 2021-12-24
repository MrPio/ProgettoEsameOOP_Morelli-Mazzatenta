package com.univpm.po.NutritionStats.exception;

import com.univpm.po.NutritionStats.enums.Api;

public class ApiFoodNotFoundException extends Exception{
    final static String BASE_MESSAGE="Sorry, but we couldn't find what you are looking for in api. More details here:";

    private Api api;
    private long eanCode;
    private String foodName;

    public ApiFoodNotFoundException(long eanCode) {
        super(BASE_MESSAGE);
        this.api=Api.CHOMP;
        this.eanCode = eanCode;
    }

    public ApiFoodNotFoundException(String foodName) {
        super(BASE_MESSAGE);
        this.api=Api.EDAMAM;
        this.foodName = foodName;
    }

    public Api getApi() {
        return api;
    }
}
