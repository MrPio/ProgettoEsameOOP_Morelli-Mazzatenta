package com.univpm.po.NutritionStats.enums;

import com.univpm.po.NutritionStats.service.statistic.*;

/**
 * Represents a type of statistic with the respective class. Statistics are:
 * percentage, mean, standard deviation and correlation.
 *
 * @author Valerio Morelli
 */
public enum StatisticType {
    PERCENTAGE(Percentage.class),
    MEAN(Mean.class),
    STANDARD_DEVIATION(StandardDeviation.class),
    CORRELATION(Correlation.class);

    Class<? extends Statistic> referenceClass;

    /**
     * Class constructor that instantiates a statistic type with the respective class.
     *
     * @param referenceClass it can be an unknown type through generics and with
     *                       unbounded wildcard <?>
     */
    StatisticType(Class<? extends Statistic> referenceClass) {
        this.referenceClass = referenceClass;
    }

    /**
     * @return respective class of statistic type
     */
    public Class<? extends Statistic> getReferenceClass() {
        return referenceClass;
    }
}
