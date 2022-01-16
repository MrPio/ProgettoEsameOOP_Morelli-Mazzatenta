package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.exception.ApiFoodNotFoundException;
import com.univpm.po.NutritionStats.model.Food;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EdamamNutritionAnalysisAPITest {

    /**
     * Try to get nutrition info about the food name "orange".
     * @throws ApiFoodNotFoundException when the food name can't be found inside the database.
     */
    @Test
    void getFoodInfo() throws ApiFoodNotFoundException {
        JSONObject response=EdamamNutritionAnalysisAPI.getFoodInfo("orange 100GR");
        Assertions.assertNotNull(response);
        System.out.println(response.get("calories"));
        System.out.println(response.get("healthLabels"));
        System.out.println(response.get("totalWeight"));
        System.out.println(((JSONObject)response.get("totalNutrients")).get("VITC"));
    }

    /**
     * Try to instantiate a {@code Food} object by nutrition info about the food name "orange".
     * @throws ApiFoodNotFoundException when the food name can't be found inside the database.
     * @see     com.univpm.po.NutritionStats.model.Food
     */
    @Test
    void getFood() throws ApiFoodNotFoundException {
        Food food=EdamamNutritionAnalysisAPI.getFood("orange",100, Measure.GR);
        Assertions.assertEquals(food.getName(),"orange");
    }
}