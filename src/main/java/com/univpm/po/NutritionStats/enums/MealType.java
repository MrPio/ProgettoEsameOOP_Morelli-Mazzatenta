package com.univpm.po.NutritionStats.enums;

public enum MealType {
	BREAKFAST(0.20f, "Good morning! Mind to take note of your breakfast?"),
	LUNCH(0.40f, "Hey! What good meals got on your table today?"),
	SNACK(0.10f, "Take some time to write down your diary! Have you eaten any snacks?"),
	DINNER(0.30f, "What a good smell! Mind to take note of your dinner?");

	private float dailyNeed;
	private String message;

	MealType(float dailyNeed, String message) {
		this.dailyNeed = dailyNeed;
		this.message = message;
	}

	public float getDailyNeed() {
		return dailyNeed;
	}

	public String getMessage() {
		return message;
	}
}