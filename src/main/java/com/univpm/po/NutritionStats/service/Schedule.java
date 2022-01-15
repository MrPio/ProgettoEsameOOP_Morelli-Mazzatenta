package com.univpm.po.NutritionStats.service;

import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Mailbox;
import com.univpm.po.NutritionStats.model.Message;
import com.univpm.po.NutritionStats.utility.InputOutput;
import com.univpm.po.NutritionStats.utility.Serialization;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

@Configuration
@EnableScheduling
public class Schedule implements Serializable {
    final String CRON_BREAKFAST = "0 30 10 * * *";
    final String CRON_LUNCH = "0 15 14 * * *";
    final String CRON_SNACK = "0 45 17 * * *";
    final String CRON_DINNER = "0 00 21 * * *";

    final String[] breakfastMessages = {
            "Hey how is it going? If you have a minute please tell us your wonderful breakfast!"
    };
    final String[] lunchMessages = {
            "Good afternoon! Please take some time to fill your diary!"
    };
    final String[] snackMessages = {
            "Did you have some snacks?!"
    };
    final String[] dinnerMessages = {
            "Good evening! What was your super dinner?"
    };

    InputOutput localDatabase = new InputOutput(Mailbox.DIR, "");
    InputOutput localDiaryDatabase = new InputOutput(Diary.DIR, "");

    @Scheduled(cron = CRON_BREAKFAST)
    public void BreakfastReminder() {
        postMessageIf(new Message(breakfastMessages[(int) (Math.random() * breakfastMessages.length)]),
                diary -> {
                    for (var day : diary.getDayList())
                        if (day.getDate().isEqual(LocalDate.now()))
                            for (var meal : day.getMealList())
                                if (meal.getMealType().equals(MealType.BREAKFAST))
                                    return false;
                    return true;
                }
        );
    }

    @Scheduled(cron = CRON_LUNCH)
    public void LunchReminder() {
        postMessageIf(new Message(lunchMessages[(int) (Math.random() * breakfastMessages.length)]),
                diary -> {
                    for (var day : diary.getDayList())
                        if (day.getDate().isEqual(LocalDate.now()))
                            for (var meal : day.getMealList())
                                if (meal.getMealType().equals(MealType.LUNCH))
                                    return false;
                    return true;
                }
        );
    }

    @Scheduled(cron = CRON_SNACK)
    public void SnackReminder() {
        postMessageIf(new Message(snackMessages[(int) (Math.random() * breakfastMessages.length)]),
                diary -> {
                    for (var day : diary.getDayList())
                        if (day.getDate().isEqual(LocalDate.now()))
                            for (var meal : day.getMealList())
                                if (meal.getMealType().equals(MealType.SNACK))
                                    return false;
                    return true;
                }
        );
    }

    @Scheduled(cron = CRON_DINNER)
    public void DinnerReminder() {
        postMessageIf(new Message(dinnerMessages[(int) (Math.random() * breakfastMessages.length)]),
                diary -> {
                    for (var day : diary.getDayList())
                        if (day.getDate().isEqual(LocalDate.now()))
                            for (var meal : day.getMealList())
                                if (meal.getMealType().equals(MealType.DINNER))
                                    return false;
                    return true;
                }
        );
    }

    private void postMessageIf(Message message, Predicate<Diary> filter) {
        ArrayList<Diary> diaries = new ArrayList<>();
        for (var file : localDiaryDatabase.listFilesInDirectory()) {
            var diary = (Diary) new Serialization(file.getParent() + "/", file.getName()).loadObject();
            if (filter.test(diary))
                diaries.add(diary);
        }
        for (var diary : diaries) {
            diary.getUser().loadMailbox();
            diary.getUser().getMailBox().sendMessage(message).save(diary.getUser().generateToken());
        }

    }
}
