package com.univpm.po.NutritionStats.service.filter;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.Meal;

/**
 * Represents a specific type of filter which is filter by nutrients or not
 * nutrients. It means that you remove all the other nutrient or not nutrients
 * from the diary.
 *
 * @author Davide
 */
public class FilterByNutrientNotNutrient extends Filter {

    private AllNutrientNonNutrient[] names;

    /**
     * Class constructor that instantiates a filter by nutrients or not nutrients
     * with the nutrients or not nutrients you want filter.
     *
     * @param names Array of nutrients or not nutrients you want to filter
     */
    public FilterByNutrientNotNutrient(AllNutrientNonNutrient[] names) {
        this.names = names;
    }

    /**
     * Removes all the nutrients or not nutrients on diary except the ones you want
     * to filter. It checks every day on the day list, every meal in the meal list
     * and every food in the food list. If it finds the same nutrients or not
     * nutrients in nutrient list and not nutrient list of a food it doesn't remove
     * them, otherwise yes.
     */
    @Override
    public void filter(Diary diary) {
        for (Day day : diary.getDayList())
            for (Meal meal : day.getMealList())
                for (Food food : meal.getFoodList()) {
                    food.getNutrientList().removeIf(nutrient -> !contains(nutrient));
                    food.getNotNutrientList().removeIf(notNutrient -> !contains(notNutrient));
                }
    }

    /**
     * Checks if the Class of the element given by parameter is equals to the Class
     * of a nutrient or not nutrient.
     *
     * @param element whose class has to be checked
     * @return true if if the Class of the element given by parameter is equals to
     * the Class of a nutrient or not nutrient, false otherwise
     */
    private boolean contains(Object element) {
        for (var name : names)
            if (name.getReferenceClass() == element.getClass())
                return true;
        return false;
    }
}
