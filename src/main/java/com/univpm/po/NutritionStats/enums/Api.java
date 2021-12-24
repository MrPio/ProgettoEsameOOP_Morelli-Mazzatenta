package com.univpm.po.NutritionStats.enums;

public enum Api {
    CHOMP("https://chompthis.com/api/"),
    EDAMAM("https://www.edamam.com/");

    private String web;

    Api(String web) {
        this.web = web;
    }

    public String getWeb() {
        return web;
    }
}
