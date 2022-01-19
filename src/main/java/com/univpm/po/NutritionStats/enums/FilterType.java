package com.univpm.po.NutritionStats.enums;

import com.univpm.po.NutritionStats.service.filter.Filter;
import com.univpm.po.NutritionStats.service.filter.FilterByDate;
import com.univpm.po.NutritionStats.service.filter.FilterByFood;
import com.univpm.po.NutritionStats.service.filter.FilterByMealType;

/**
 * Represents a type of filter with the respective class. Filters are by date,
 * by meal type and by food.
 *
 * @author Valerio Morelli
 */
public enum FilterType {

    FILTER_BY_DATE(FilterByDate.class),
    FILTER_BY_MEAL_TYPE(FilterByMealType.class),
    FILTER_BY_FOOD(FilterByFood.class);

    Class<? extends Filter> referenceClass;

    /**
     * Class constructor that instantiates a filter type with the respective class.
     *
     * @param referenceClass it can be an unknown type through generics and with
     *                       unbounded wildcard <?>
     */
    FilterType(Class<? extends Filter> referenceClass) {
        this.referenceClass = referenceClass;
    }

    /**
     * @return respective class of filter type
     */
    public Class<? extends Filter> getReferenceClass() {
        return referenceClass;
    }
}
