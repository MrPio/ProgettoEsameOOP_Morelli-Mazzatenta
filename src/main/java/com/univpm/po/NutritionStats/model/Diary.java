package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.api.DropboxAPI;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.enums.StatisticType;
import com.univpm.po.NutritionStats.service.filter.Filter;
import com.univpm.po.NutritionStats.service.statistic.Statistic;
import com.univpm.po.NutritionStats.utility.InputOutput;
import com.univpm.po.NutritionStats.utility.Serialization;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Diary implements Serializable {
    private static final long serialVersionUID = 1L;
    public final static String DIR = "database/";
    public final static String DROPBOX_DIR = "/database/";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private User user;
    private ArrayList<Day> dayList;
    private Map<AllNutrientNonNutrient, Float> sumValues = new HashMap<>() {
    };

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

    public Map<AllNutrientNonNutrient, Float> getSumValues() {
        sumValues = new HashMap<>() {
        };
        for (var nutrient : AllNutrientNonNutrient.values()) {
            float sum = 0.0f;
            for (var day : dayList)
                sum += day.calculate(nutrient.getReferenceClass());
            if (sum > 0.0000001f &&
                    nutrient != AllNutrientNonNutrient.SUGAR &&
                    nutrient != AllNutrientNonNutrient.SATURATED)
                sumValues.put(nutrient, sum);
        }
        return sumValues;
    }

    public float getTotalCalories() {
        float calories = 0;
        for (var day : dayList)
            calories += day.getTotalCalories();
        return calories;
    }

    public static Diary load(String userToken) {
        // Check if I already have the information needed:
        // in local database
        InputOutput inputOutputEan = new InputOutput(Diary.DIR, userToken + ".dat");
        if (inputOutputEan.existFile()) {
            Serialization serializationResult = new Serialization(Diary.DIR, userToken + ".dat");
            return (Diary) serializationResult.loadObject();
        }
        // in remote database
        if (DropboxAPI.getFilesInFolder(Diary.DROPBOX_DIR).contains(userToken + ".dat")) {
            DropboxAPI.downloadFile(Diary.DROPBOX_DIR + userToken + ".dat", Diary.DIR + userToken + ".dat");
            Serialization serializationResult = new Serialization(Diary.DIR, userToken + ".dat");
            return (Diary) serializationResult.loadObject();
        }
        return null;
    }

    public void save() {
        Serialization s = new Serialization(DIR, user.generateToken() + ".dat");
        s.saveObject(this);
        DropboxAPI.uploadFile(new File(s.getFullPath()), DROPBOX_DIR);
    }

    public void reset() {
        Map.Entry lastEntry = user.getWeight().lastEntry();
        user.setWeight(new TreeMap<>() {{
            putAll(Map.ofEntries(lastEntry));
        }});
        dayList = new ArrayList<>();
        save();
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
            Day dayToAdd = new Day(stringToLocalDate(dayId));
            dayToAdd.addFood(mealType, food);
            dayList.add(dayToAdd);
        }
        save();
    }

    public static LocalDate stringToLocalDate(String date){
        String[] values=date.replace("-", "/").split("/");
        String formatted=String.format("%02d",Integer.parseInt(values[0]))+"/"
                +Integer.parseInt(values[1]) +"/"+ Integer.parseInt(values[2]);
        return LocalDate.parse(formatted, formatter);
    }

    public void addWater(String dayId, Water water) {
        Day requestedDay = findDayById(dayId);
        if (requestedDay != null)
            requestedDay.addWater(water);
        else {
            Day dayToAdd = new Day(LocalDate.parse(dayId.replace("-", "/"), formatter));
            dayToAdd.addWater(water);
            dayList.add(dayToAdd);
        }
        save();
    }

    public void updateWeight(float newWeight, LocalDate date) {
        user.getWeight().put(date, newWeight);
        save();
    }

    public JSONObject doStatistic(ArrayList<Filter> filters, StatisticType... statisticType)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        doFilter(filters);
        JSONObject response = new JSONObject();

        response.put("result", "success");
        for (var stat : statisticType) {
            Statistic statistic = stat.getReferenceClass().getDeclaredConstructor().newInstance();
            statistic.calculateStatistic(this);
            response.put(stat, statistic);
        }
        return response;
    }

    public Diary doFilter(ArrayList<Filter> filters) {
        for (var filter : filters)
            filter.filter(this);
        removeEmptyDays();
        return this;
    }

    private void removeEmptyDays() {
        dayList.removeIf(day -> day.getMealList().size() == 0 && day.getWaterList().size() == 0);
    }

    public JSONObject toJsonObject() {
        JSONObject toJsonObject = new JSONObject();
        toJsonObject.put("diary", this);
        return toJsonObject;
    }

}