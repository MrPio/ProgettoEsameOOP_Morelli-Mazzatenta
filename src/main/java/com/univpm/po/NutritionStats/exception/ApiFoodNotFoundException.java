package com.univpm.po.NutritionStats.exception;

import com.univpm.po.NutritionStats.enums.Api;

/**
 * The {@code ApiFoodNotFoundException} class is an {@link Exception} thrown when a search on
 * <p>•<strong>Edamam</strong> or
 * <p>•<strong>Chomp</strong> api
 * <p>provides no result.
 *
 * @author Valerio Morelli
 */
public class ApiFoodNotFoundException extends Exception {
    final static String BASE_MESSAGE = "Sorry, but we couldn't find what you are looking for in api.";
    private final Api api;
    private long eanCode;
    private String foodName;

    /**
     * The constructor for the exception occurred while looking for an ean code on Chomp api.
     *
     * @param eanCode the eanCode requested by the client which provided no result.
     */
    public ApiFoodNotFoundException(long eanCode) {
        super(BASE_MESSAGE);
        this.api = Api.CHOMP;
        this.eanCode = eanCode;
    }

    /**
     * The constructor for the exception occurred while looking for a food name on Edamam api.
     *
     * @param foodName the food name requested by the client which provided no result.
     */
    public ApiFoodNotFoundException(String foodName) {
        super(BASE_MESSAGE);
        this.api = Api.EDAMAM;
        this.foodName = foodName;
    }

    /**
     * @return the name of the api where this exception occurred.
     */
    public Api getApi() {
        return api;
    }

    /**
     * @return the ean code given by the client.
     */
    public long getEanCode() {
        return eanCode;
    }

    /**
     * @return the food name given by the client.
     */
    public String getFoodName() {
        return foodName;
    }
}
