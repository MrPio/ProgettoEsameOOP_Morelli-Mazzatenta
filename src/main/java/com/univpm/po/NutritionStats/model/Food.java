package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.model.nutrient.NotNutrient;
import com.univpm.po.NutritionStats.model.nutrient.Nutrient;
import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.nutrient.*;
import org.json.simple.JSONObject;

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

    public Diet getDiet() {
        return diet;
    }

    public ArrayList<Nutrient> getNutrientList() {
        return nutrientList;
    }

    public ArrayList<NotNutrient> getNotNutrientList() {
        return notNutrientList;
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

    public int getTotalCalories() {
        float foodCalories = 0;
        for (Nutrient nutrient : nutrientList) {
            if (nutrient instanceof MacroNutrient)
                foodCalories += ((MacroNutrient) nutrient).calculateCalories();
        }
        return (int) foodCalories;
    }

    public void newPortionWeight(int newPortionWeight){
        float scale=(float)newPortionWeight/portionWeight;
        portionWeight=newPortionWeight;
        for(var nutrient:nutrientList) {
            nutrient.setQuantity(nutrient.getQuantity() * scale);
            if(nutrient instanceof Carbohydrate)
                ((Carbohydrate)nutrient).setQuantityOnlySugar(((Carbohydrate)nutrient).getQuantityOnlySugar()*scale);
            else if(nutrient instanceof Lipid)
                ((Lipid)nutrient).setQuantityOnlySaturated(((Lipid)nutrient).getQuantityOnlySaturated()*scale);
        }
        for(var notNutrient:notNutrientList)
            notNutrient.setQuantity(notNutrient.getQuantity()*scale);
    }

    public <T> float calculate(Class<T> myClass) {
        if (Nutrient.class.isAssignableFrom(myClass)) {
            for (Nutrient nutrient : nutrientList)
                if (myClass.isInstance(nutrient))
                    return nutrient.getQuantity();
        }
        else if (NotNutrient.class.isAssignableFrom(myClass)) {
            for (NotNutrient notNutrient : notNutrientList)
                if (myClass.isInstance(notNutrient))
                    return notNutrient.getQuantity();
        }
        return 0.0f;
    }
}
