package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.api.DropboxAPI;
import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.utility.InputOutputImpl;
import com.univpm.po.NutritionStats.utility.SerializationImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Diary implements Serializable {
    private static final long serialVersionUID = 1L;
    public final static String DIR = "database/";
    public final static String DROPBOX_DIR = "/database/";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private User user;
    private ArrayList<Day> dayList;

    public Diary() {
        this.dayList = new ArrayList<>();
    }

    public Diary(User user) {
        this.user = user;
        this.dayList = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Day> getDayList() {
        return dayList;
    }

    public static Diary load(String userToken) {
        // Check if I already have the information needed:
        // in local database
        InputOutputImpl inputOutputEan = new InputOutputImpl(Diary.DIR, userToken + ".dat");
        if (inputOutputEan.existFile()) {
            SerializationImpl serializationResult = new SerializationImpl(Diary.DIR, userToken + ".dat");
            return (Diary) serializationResult.loadObject();
        }
        // in remote database
        if (DropboxAPI.getFilesInFolder(Diary.DROPBOX_DIR).contains(userToken + ".dat")) {
            DropboxAPI.downloadFile(Diary.DROPBOX_DIR + userToken + ".dat", Diary.DIR + userToken + ".dat");
            SerializationImpl serializationResult = new SerializationImpl(Diary.DIR, userToken + ".dat");
            return (Diary) serializationResult.loadObject();
        }
        return null;
    }

    public void save() {
        SerializationImpl s = new SerializationImpl(DIR, user.generateToken() + ".dat");
        s.saveObject(this);
        DropboxAPI.uploadFile(new File(s.getFullPath()), DROPBOX_DIR);
    }

    public Day findDayById(String dayId) {
        dayId = dayId.replace("-", "/");
        for (Day day : dayList) {
            String thisDayId = day.calculateDayId();
            if (dayId.equals(thisDayId))
                return day;
        }
        return null;
    }

    public void addFood(String dayId, MealType mealType, Food food) {
        Day requestedDay = findDayById(dayId);
        if (requestedDay != null)
            requestedDay.addFood(mealType, food);
        else {
            Day dayToAdd = new Day(LocalDate.parse(dayId.replace("-", "/"), formatter));
            dayToAdd.addFood(mealType, food);
            dayList.add(dayToAdd);
        }
        save();
    }

    public void addWater(String dayId, Water water) {
        Day requestedDay = findDayById(dayId);
        if (requestedDay != null)
            requestedDay.addWater(water);
        else {
            Day dayToAdd = new Day(LocalDate.parse(dayId.replace("-", "/"), formatter));
            dayToAdd.addWater( water);
            dayList.add(dayToAdd);
        }
        save();
    }

    public JSONObject toJsonObject() {
        JSONObject toJsonObject = new JSONObject();
        toJsonObject.put("diary", this);
        return toJsonObject;
    }

}