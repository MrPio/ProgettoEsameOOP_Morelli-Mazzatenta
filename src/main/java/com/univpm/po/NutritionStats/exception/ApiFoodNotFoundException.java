package com.univpm.po.NutritionStats.exception;

import com.univpm.po.NutritionStats.enums.Api;

public class ApiFoodNotFoundException extends Exception{
    final static String BASE_MESSAGE="sorry, but we couldn't find what you are looking for in api. More details here:\r\n";

    Api api;
    long eanCode;
    String foodName;

    public ApiFoodNotFoundException(long eanCode) {
        super(BASE_MESSAGE+"API: "+Api.CHOMP.name()+"\r\nINFO: "+Api.CHOMP.web);
        this.api=Api.CHOMP;
        this.eanCode = eanCode;
    }

    public ApiFoodNotFoundException(String foodName) {
        super(BASE_MESSAGE+"API: "+Api.EDAMAM.name()+"\r\nINFO: "+Api.EDAMAM.web);
        this.api=Api.EDAMAM;
        this.foodName = foodName;
    }
}
