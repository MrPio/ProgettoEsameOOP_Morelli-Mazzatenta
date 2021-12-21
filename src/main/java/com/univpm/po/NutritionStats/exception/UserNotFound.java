package com.univpm.po.NutritionStats.exception;

public class UserNotFound extends Exception {
    final static String BASE_MESSAGE="Sorry, but this token doesn't belong to any users, please retry.";

    private String token;

    public UserNotFound(String token) {
        super(BASE_MESSAGE+" TOKEN: "+token);
        this.token=token;
    }

    public String getToken() {
        return token;
    }
}
