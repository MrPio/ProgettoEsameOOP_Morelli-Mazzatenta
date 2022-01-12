package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.exception.ApiFoodNotFoundException;
import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.exception.ChompLimitOvercameException;
import com.univpm.po.NutritionStats.model.Food;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ChompBarcodeSearchAPITest {

    /**
     * Try to instantiate a {@code Food} object by nutrition info about the ean code "8017596065308".
     * @throws ApiFoodNotFoundException when the ean code can't be found inside the database.
     * @throws ChompLimitOvercameException when the requests exceeded the limit.
     * @see     com.univpm.po.NutritionStats.model.Food
     */
    @Test
    void getFood() throws ApiFoodNotFoundException, ChompLimitOvercameException {
        Food food= ChompBarcodeSearchAPI.getFood(8017596065308L,50);
        System.out.println(food.getName());
        Assertions.assertEquals(food.getName(),"Muesli croccante al cioccolato e nocciole");
    }
}