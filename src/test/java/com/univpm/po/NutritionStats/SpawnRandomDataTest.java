package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.model.Diary;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SpawnRandomDataTest {
    /**
     * Try to load sample diary and spawn random info. Just for debug.
     * @see com.univpm.po.NutritionStats.model.Diary
     */
    @Test
    void spawn() {
        Diary diary = Diary.load("3959de8aeefabfa1385135fa8d03ee21");
        assert diary != null;
        LocalDate start = LocalDate.now();
        float count=1;
        for (int i = 0; i < 72; i++) {
            diary.getUser().getWeight().put(start,60f+(float) Math.sin(count+=0.06f)*50 +(float) Math.random()*50);
            start = start.minusDays(1);
        }
        diary.save();
    }
}
