package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.model.nutrient.NotNutrient;
import com.univpm.po.NutritionStats.model.nutrient.Nutrient;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.nutrient.*;

import java.util.ArrayList;

public class Food {
    private String name;
    private int portionWeight;
    private Measure measure;
    private ArrayList<Nutrient> nutrientList = new ArrayList<Nutrient>();
    private ArrayList<NotNutrient> notNutrientList = new ArrayList<NotNutrient>();

    public Food(String name, int portionWeight, Measure measure) {
        this.name = name;
        this.portionWeight = portionWeight;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public int getPortionWeight() {
        return portionWeight;
    }
    
	public Measure getMeasure() {
		return measure;
	}

    public void addNutrient(Nutrient nutrient) {
        nutrientList.add(nutrient);
    }

    public void addNotNutrient(NotNutrient notNutrient) {
        notNutrientList.add(notNutrient);
    }

    public int calculateCalories() {
        float foodCalories = 0;
        for (Nutrient nutrient : nutrientList) {
            if (nutrient instanceof MacroNutrient)
                foodCalories += ((MacroNutrient) nutrient).calculateCalories();
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
