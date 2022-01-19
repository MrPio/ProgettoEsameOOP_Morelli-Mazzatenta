package com.univpm.po.NutritionStats.service.statistic;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.model.Day;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.nutrient.Carbohydrate;
import com.univpm.po.NutritionStats.model.nutrient.Lipid;
import com.univpm.po.NutritionStats.model.nutrient.MacroNutrient;
import com.univpm.po.NutritionStats.model.nutrient.Protein;
import com.univpm.po.NutritionStats.utility.Mathematics;

import java.util.HashMap;
import java.util.Map;

public class Percentage extends Statistic {
    /**
     * Since the caloric intake is not null only on {@link Carbohydrate}, {@link Lipid} and {@link Protein},
     * there is no need to calculate the percentage values on all others nutrients, seeing as how it will be a null value.
     * This is it why we make an {@code Override} of the instance variable already defined inside the superclass {@link Statistic}.
     */
    private final Map<AllNutrientNonNutrient, Float> statsValues = new HashMap<>() {
        {
            put(AllNutrientNonNutrient.CARBOHYDRATE, 0f);
            put(AllNutrientNonNutrient.LIPID, 0f);
            put(AllNutrientNonNutrient.PROTEIN, 0f);
        }
    };

    @Override
    public Map<AllNutrientNonNutrient, Float> getStatsValues() {
        return statsValues;
    }

    /**
     * <b>Calculate the percentage values based on the sample extracted on the provided filtered diary</b>
     * @param diary the instance of {@link Diary} on which the statistic will be calculated.
     * @see Statistic#extractAllNutrientNonNutrientSamples(Diary)
     */
    public void calculateStatistic(Diary diary) {
        var samples = extractAllNutrientNonNutrientSamples(diary);
        for (var entry : statsValues.entrySet())
            entry.setValue(new Mathematics(samples.get(entry.getKey())).calculateSum()
                    * MacroNutrient.CALORIES_PER_GRAM.get(entry.getKey().getReferenceClass()) * 100f
                    / new Mathematics(extractCalorieSample(diary)).calculateSum());
    }
}