package com.univpm.po.NutritionStats.model;

import java.util.ArrayList;

import com.univpm.po.NutritionStats.model.nutrient.Calcium;
import com.univpm.po.NutritionStats.model.nutrient.Nutrient;
import com.univpm.po.NutritionStats.model.nutrient.Sodium;

public class Water {

	private int volume;
	private ArrayList<Nutrient> nutrientList = new ArrayList<Nutrient>();

	public Water(int value) {
		this.volume = value;
	}

	public int getVolume() {
		return volume;
	}

	public ArrayList<Nutrient> getNutrientList() {
		return nutrientList;
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
    
	
}
