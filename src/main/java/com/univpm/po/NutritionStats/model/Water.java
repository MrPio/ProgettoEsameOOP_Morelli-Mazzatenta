package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.model.nutrient.Calcium;
import com.univpm.po.NutritionStats.model.nutrient.Nutrient;
import com.univpm.po.NutritionStats.model.nutrient.Sodium;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents water drunk which is different from the water of a food
 *
 * @author Davide Mazzatenta
 */
public class Water implements Serializable {
    final float SODIUM_PER_100 = 4.0f / 1000f;
    final float CALCIUM_PER_100 = 3.0f / 1000f;
    final float POTASSIUM_PER_100 = 0.0f / 1000f;
    final float IRON_PER_100 = 0.0f / 1000f;

    private int volume;
    private ArrayList<Nutrient> nutrientList = new ArrayList<Nutrient>();

    /**
     * Class constructor that instantiates water with a volume and add to the
     * nutrient list the default values of micronutrients in water that are sodium
     * and potassium
     *
     * @param volume
     */
    public Water(int volume) {
        this.volume = volume;
        this.nutrientList.add(new Sodium(SODIUM_PER_100 * volume / 100.0f));
        this.nutrientList.add(new Calcium(CALCIUM_PER_100 * volume / 100.0f));
    }

    /**
     * @return the water volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * @return the sodium in water if it is on the nutrient list
     */
    public float getSodium() {
        for (Nutrient nutrient : nutrientList)
            if (nutrient instanceof Sodium)
                return ((Sodium) nutrient).getQuantity();
        return 0.0f;
    }

    /**
     * @return the calcium in water if it is on the nutrient list
     */
    public float getCalcium() {
        for (Nutrient nutrient : nutrientList)
            if (nutrient instanceof Calcium)
                return ((Calcium) nutrient).getQuantity();
        return 0.0f;
    }
}
