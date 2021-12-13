package com.univpm.po.NutritionStats.model;

public class Water {
	public enum portionType{Glass,Bottle500ml,Bottle1000ml};
	private int portionVolume;
	
	public Water(int portionVolume) {
		this.portionVolume = portionVolume;
	}

	public int getPortionVolume() {
		return portionVolume;
	}
	
}
