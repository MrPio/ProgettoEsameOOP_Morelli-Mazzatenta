package com.univpm.po.NutritionStats.enums;

public enum MealType {
    BREAKFAST(0.20f),
    LUNCH(0.40f),
    SNACK(0.10f),
    DINNER(0.30f);

    float dailyNeed;

    MealType(float dailyNeed) {
        this.dailyNeed = dailyNeed;
    }
}