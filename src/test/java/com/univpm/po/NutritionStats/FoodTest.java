package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.exception.ApiFoodNotFoundException;
import com.univpm.po.NutritionStats.model.Food;
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

    /**
     * Try to instantiate a {@code Food} object with some info and display them.
     * @see     com.univpm.po.NutritionStats.model.Food
     */
    @Test
    void calculate() {
        System.out.println(food.calculate(Lipid.class));
        System.out.println(food.calculate(Fiber.class));
    }
}