package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.nutrient.Fiber;
import com.univpm.po.NutritionStats.model.nutrient.Lipid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    Food food;
    @BeforeEach
    void setUp() {
        food=new Food("c",12, Measure.GR, Diet.CLASSIC);
        food.addNutrient(new Lipid(12f,3f));
        food.addNotNutrient(new Fiber(123f));
    }

    @Test
    void calculate() throws InstantiationException, IllegalAccessException {
        //System.out.println(food.calculate(Lipid.class));
        System.out.println(food.calculate(Fiber.class));
    }
}