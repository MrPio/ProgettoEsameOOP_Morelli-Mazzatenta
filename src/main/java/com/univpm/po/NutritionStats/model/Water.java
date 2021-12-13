package com.univpm.po.NutritionStats.model;

public class Water {
	public enum portionType {
		GLASS(200),
		CUP(350),
		BOTTLE_500(500),
		BOTTLE_1000(1000);
		public int value;

		portionType(int value) {
			this.value = value;
		}
};
	private portionType value;

	public Water(portionType value) {
		this.value = value;
	}

	public portionType getValue() {
		return value;
	}
}
