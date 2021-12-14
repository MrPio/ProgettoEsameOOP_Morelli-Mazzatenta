package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.model.Food;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChompBarcodeSearchAPITest {

    @Test
    void getFood() {
        Food food= ChompBarcodeSearchAPI.getFood("8017596065308",50);
    }
}