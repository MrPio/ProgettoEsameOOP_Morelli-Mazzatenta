package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.model.nutrient.NotNutrient;
import com.univpm.po.NutritionStats.model.nutrient.Nutrient;
import com.univpm.po.NutritionStats.model.nutrient.*;

import java.util.ArrayList;

public class Food {
    private String name;
    private int portionWeight;
    private ArrayList<Nutrient> nutrientList = new ArrayList<Nutrient>();
    private ArrayList<NotNutrient> notNutrientList = new ArrayList<NotNutrient>();

    public Food(String name, int portionWeight) {
        this.name = name;
        this.portionWeight = portionWeight;
    }

    public String getName() {
        return name;
    }

    public int getPortionWeight() {
        return portionWeight;
    }

    public void addNutrient(Nutrient n) {
        nutrientList.add(n);
    }

    public void addNotNutrient(NotNutrient notN) {
        notNutrientList.add(notN);
    }

    public int calculateFoodCalories() {
        float foodCalories = 0;
        for (Nutrient n : nutrientList) {
            if (n instanceof MacroNutrient)
                foodCalories += ((MacroNutrient) n).calculateCalories();
        }
        return (int) foodCalories;
    }

    public float getCarbohydrates() {
        for (Nutrient nutrient : nutrientList)
            if (nutrient instanceof Carbohydrate)
                return ((Carbohydrate) nutrient).getQuantity();
        return 0.0f;
    }

    public float getProtein() {
        for (Nutrient nutrient : nutrientList)
            if (nutrient instanceof Protein)
                return ((Protein) nutrient).getQuantity();
        return 0.0f;
    }

    public float getLipids() {
        for (Nutrient nutrient : nutrientList)
            if (nutrient instanceof Lipid)
                return ((Lipid) nutrient).getQuantity();
        return 0.0f;
    }
}
