package com.univpm.po.NutritionStats.enums;

/**
 * Represents chomp and edamam API web URL as a string.
 *
 * @author Valerio Morelli
 */
public enum Api {
    CHOMP("https://chompthis.com/api/"),
    EDAMAM("https://www.edamam.com/");

    private String web;

    /**
     * Class constructor that instantiates a API web URL as a string.
     *
     * @param web URL as a string
     */
    Api(String web) {
        this.web = web;
    }

    /**
     * @return API web URL as a string
     */
    public String getWeb() {
        return web;
    }
}
