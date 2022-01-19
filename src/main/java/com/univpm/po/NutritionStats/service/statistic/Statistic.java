package com.univpm.po.NutritionStats.service.statistic;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Diary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>{@code Statistic} is an abstract class that will be instantiated with its subclasses which are all the type of
 * statistic that can be calculated on a provided filtered diary.</b>
 *
 * <p> The supported statistics are the following:
 * <p> •<b>Sample mean</b> about all nutrition type,
 * <p> •<b>Sample standard deviation</b> about all nutrition type,
 * <p> •<b>Percentage values</b> about caloric intake,
 * <p> •<b>Correlation</b> between calories and weight tendency.
 *
 * @author Davide Mazzatenta
 */
public abstract class Statistic {
    /**
     * An HashMap containing a Float value for each nutrient type. The meaning of the {@code Values} depends
     * on the subclass which assign those {@code Values}. For example, the subclass {@link Mean} will put
     * the value of the sample mean.
     */
    protected Map<AllNutrientNonNutrient, Float> statsValues = new HashMap<>() {
        {
            for (AllNutrientNonNutrient nutrient : AllNutrientNonNutrient.values())
                if (nutrient != AllNutrientNonNutrient.SUGAR && nutrient != AllNutrientNonNutrient.SATURATED)
                    put(nutrient, 0.0f);
        }
    };

    public Map<AllNutrientNonNutrient, Float> getStatsValues() {
        return statsValues;
    }

    /**
     * abstract method implemented by the subclass
     *
     * @param diary the instance of {@link Diary} on which the statistic will be calculated.
     */
    public abstract void calculateStatistic(Diary diary);

    /**
     * method (unused) to reset the values of the instance variable {@code statsValues}.
     */
    protected void resetValues() {
        for (Map.Entry<AllNutrientNonNutrient, Float> entry : statsValues.entrySet())
            entry.setValue(0f);
    }

    /**
     * <b>Method used to extract the <i>random sample</i> from the filtered diary for each
     * {@link AllNutrientNonNutrient nutrient type} which will be used to calculate the statistics</b>
     * <i>(intended as a function of independent random variables of a random sample)</i>
     *
     * @param diary the instance of {@link Diary} from where the sample is taken.
     * @return an {@link HashMap} containing a random sample for each {@link AllNutrientNonNutrient nutrient type}.
     */
    protected Map<AllNutrientNonNutrient, ArrayList<Float>> extractAllNutrientNonNutrientSamples(Diary diary) {
        Map<AllNutrientNonNutrient, ArrayList<Float>> result = new HashMap<>();
        for (var entry : statsValues.entrySet()) {
            ArrayList<Float> values = new ArrayList<>();
            for (var day : diary.getDayList())
                values.add(day.calculate(entry.getKey().getReferenceClass()));
            result.put(entry.getKey(), values);
        }
        return result;
    }

    /**
     * <b>Method used to extract the <i>random sample</i> of calories from the filtered diary
     * which will be used to calculate the statistics</b>
     * <i>(intended as a function of independent random variables of a random sample)</i>
     *
     * @param diary the instance of {@link Diary} from where the sample is taken.
     * @return an {@link HashMap} containing a random sample for each {@link AllNutrientNonNutrient nutrient type}.
     */
    protected ArrayList<Float> extractCalorieSample(Diary diary) {
        ArrayList<Float> values = new ArrayList<>();
        for (var day : diary.getDayList())
            values.add(day.getTotalCalories());
        return values;
    }

    /**
     * <b>Method used to extract the <i>random sample</i> of weights from the filtered diary
     * which will be used to calculate the statistics</b>
     * <i>(intended as a function of independent random variables of a random sample)</i>
     *
     * @param diary the instance of {@link Diary} from where the sample is taken.
     * @return an {@link HashMap} containing a random sample for each {@link AllNutrientNonNutrient nutrient type}.
     */
    protected ArrayList<Float> extractWeightSample(Diary diary) {
        ArrayList<Float> values = new ArrayList<>();
        for (var date : diary.getUser().getWeight().keySet())
            values.add(diary.getUser().getWeight().get(date));
        return values;
    }
}
