package com.univpm.po.NutritionStats.exception;

import com.univpm.po.NutritionStats.enums.Api;

public class UserAlreadyInDatabase extends Exception{
    final static String BASE_MESSAGE="Sorry, but the email you entered is already used by another account";

    String email;

    public UserAlreadyInDatabase(String email) {
        super(BASE_MESSAGE+"\r\nEMAIL: "+email);
        this.email=email;
    }
}
