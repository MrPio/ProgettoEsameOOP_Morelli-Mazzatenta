package com.univpm.po.NutritionStats.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EdamamApiImpl {
    String edamamApiUrl = "";
    HttpURLConnection edamamApiConnection;

    public EdamamApiImpl() {
        try {
            edamamApiConnection = (HttpURLConnection) new URL(edamamApiUrl).openConnection();
        } catch (IOException e) {

        }


    }


}
