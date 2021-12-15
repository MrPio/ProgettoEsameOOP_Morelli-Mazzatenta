package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.exception.ApiFoodNotFoundException;
import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.model.Food;
import org.junit.jupiter.api.Test;

class ChompBarcodeSearchAPITest {

    @Test
    void getFood() throws ApiFoodNotFoundException {
        Food food= ChompBarcodeSearchAPI.getFood(8017596065308L,50);
    }
}