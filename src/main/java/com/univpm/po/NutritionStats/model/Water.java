package com.univpm.po.NutritionStats.model;

public class Water {
	public enum portionType {
		Glass(200),
		Cup(350),
		Bottle500ml(500),
		Bottle1000ml(1000);
		public int value;

		private portionType(int value) {
			this.value = value;
		}
};
	private int portionVolume;
	
	public Water(int portionVolume) {
		this.portionVolume = portionVolume;
	}

	public int getPortionVolume() {
		return portionVolume;
	}
	
}
