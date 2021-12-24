package com.univpm.po.NutritionStats.service;

import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.utility.InputOutputImpl;
import com.univpm.po.NutritionStats.utility.SerializationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

@Configuration
@EnableScheduling
public class Schedule {
    final String CRON_BREAKFAST = "*/1 * * * * *";
    final String CRON_LUNCH = "0 15 14 * * *";
    final String CRON_SNACK = "0 45 17 * * *";
    final String CRON_DINNER = "0 00 21 * * *";

/*    InputOutputImpl localDatabase = new InputOutputImpl(Diary.DIR, "");
    HashMap<String, Diary> localDataBase = new HashMap<>() {
        {
            for (File file : localDatabase.listFilesInDirectory())
                put(file.getName(), (Diary) new SerializationImpl(file.getParent()+"/", file.getName()).loadObject());
        }
    };*/

/*    @Scheduled(cron = CRON_BREAKFAST)
    public void BreakfastReminder() {
        System.out.println(Arrays.toString(localDatabase.listFilesInDirectory()));
    }

    @Scheduled(cron = CRON_LUNCH)
    public void LunchReminder() {

    }

    @Scheduled(cron = CRON_SNACK)
    public void SnackReminder() {

    }

    @Scheduled(cron = CRON_DINNER)
    public void DinnertReminder() {

    }*/
}