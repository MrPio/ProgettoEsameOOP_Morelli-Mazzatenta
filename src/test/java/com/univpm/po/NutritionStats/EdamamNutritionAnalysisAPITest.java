package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.Food;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdamamNutritionAnalysisAPITest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getFoodInfo() {
        JSONObject jO=EdamamNutritionAnalysisAPI.getFoodInfo("orange-100gr");
        System.out.println(jO.get("calories"));
        System.out.println(jO.get("healthLabels"));
        System.out.println(jO.get("totalWeight"));
        System.out.println(((JSONObject)jO.get("totalNutrients")).get("VITC"));
    }

    @Test
    void getFood() {
        Food food=EdamamNutritionAnalysisAPI.getFood("orange",100, Measure.GR);
    }
}