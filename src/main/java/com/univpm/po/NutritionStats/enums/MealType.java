package com.univpm.po.NutritionStats.enums;

/**
 * Represents a meal type, which is breakfast, lunch, snack or dinner with their
 * percentage of daily caloric intake and a message.
 * 
 * @author Davide
 *
 */
public enum MealType {
	BREAKFAST(0.20f, "Good morning! Mind to take note of your breakfast?"),
	LUNCH(0.40f, "Hey! What good meals got on your table today?"),
	SNACK(0.10f, "Take some time to write down your diary! Have you eaten any snacks?"),
	DINNER(0.30f, "What a good smell! Mind to take note of your dinner?");

	private float dailyNeed;
	private String message;

	/**
	 * Class constructor that instantiates a meal type with the percentage of daily
	 * caloric intake and a message.
	 * 
	 * @param dailyNeed
	 * @param message
	 */
	MealType(float dailyNeed, String message) {
		this.dailyNeed = dailyNeed;
		this.message = message;
	}

	/**
	 * @return the percentage of daily caloric intake
	 */
	public float getDailyNeed() {
		return dailyNeed;
	}

	/**
	 * @return message associated with the meal type
	 */
	public String getMessage() {
		return message;
	}
}