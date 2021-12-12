package com.univpm.po.NutritionStats.Model;

public class Carbohydrate extends MacroNutrient {
	private float value;
	private float valueOnlySugar;
	
	public Carbohydrate(float value, float valueOnlySugar) {
		super();
		this.value = value;
		this.valueOnlySugar = valueOnlySugar;
	}
	
	public float getValue() {
		return value;
	}


	public float getValueOnlySugar() {
		return valueOnlySugar;
	}

	@Override
	public float calculateCalories() {
		return 0;
	}

}
