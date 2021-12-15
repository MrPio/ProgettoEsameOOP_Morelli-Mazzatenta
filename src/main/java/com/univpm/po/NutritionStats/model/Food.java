package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.model.nutrient.NotNutrient;
import com.univpm.po.NutritionStats.model.nutrient.Nutrient;
import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.nutrient.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Food implements Serializable {
    private String name;
    private int portionWeight;
    private Measure measure;
    private Diet diet;
    private ArrayList<Nutrient> nutrientList = new ArrayList<Nutrient>();
    private ArrayList<NotNutrient> notNutrientList = new ArrayList<NotNutrient>();

    public Food(String name, int portionWeight, Measure measure, Diet diet) {
        this.name = name;
        this.portionWeight = portionWeight;
        this.measure = measure;
        this.diet = diet;
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

    public int getCalories() {
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
    
    public float getVitaminA() {
    	for (Nutrient nutrient : nutrientList)
    		if (nutrient instanceof VitaminA)
    			return ((VitaminA)nutrient).getQuantity();
    	return 0.0f;
    }
    
    public float getVitaminC() {
    	for (Nutrient nutrient : nutrientList)
    		if (nutrient instanceof VitaminC)
    			return ((VitaminC)nutrient).getQuantity();
    	return 0.0f;
    }
    
    public float getSodium() {
    	for (Nutrient nutrient : nutrientList)
    		if (nutrient instanceof Sodium)
    			return ((Sodium)nutrient).getQuantity();
    	return 0.0f;
    }
    
    public float getCalcium() {
    	for (Nutrient nutrient : nutrientList)
    		if (nutrient instanceof Calcium)
    			return ((Calcium)nutrient).getQuantity();
    	return 0.0f;
    }
    
    public float getPotassium() {
    	for (Nutrient nutrient : nutrientList)
    		if (nutrient instanceof Potassium)
    			return ((Potassium)nutrient).getQuantity();
    	return 0.0f;
    }
    
    public float getIron() {
    	for (Nutrient nutrient : nutrientList)
    		if (nutrient instanceof Iron)
    			return ((Iron)nutrient).getQuantity();
    	return 0.0f;
    }
    
    public float getFiber() {
    	for (NotNutrient notNutrient : notNutrientList)
    		if (notNutrient instanceof Fiber)
    			return ((Fiber)notNutrient).getQuantity();
    	return 0.0f;
    }
    
    public float getWaterFromFood() {
    	for (NotNutrient notNutrient : notNutrientList)
    		if (notNutrient instanceof WaterFromFood)
    			return ((WaterFromFood)notNutrient).getQuantity();
    	return 0.0f;
    }
}
