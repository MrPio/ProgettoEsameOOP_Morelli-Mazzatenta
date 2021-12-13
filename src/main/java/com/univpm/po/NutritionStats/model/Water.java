package com.univpm.po.NutritionStats.model;

public class Water {
	public enum portionType {
		GLASS(200),
		CUP(350),
		BOTTLE_500(500),
		BOTTLE_1000(1000);
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
