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

/**
 * Represents the user's diary with all the information about him and his
 * eating.
 *
 * @author Davide Mazzatenta
 */
public class Diary implements Serializable {
    private static final long serialVersionUID = 1L;
    public final static String DIR = "database/";
    public final static String DROPBOX_DIR = "/database/";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private User user;
    private ArrayList<Day> dayList;
    private Map<AllNutrientNonNutrient, Float> sumValues = new HashMap<>() {
    };

    /**
     * Class first constructor that instantiates a diary with empty day list.
     */
    public Diary() {
        this.dayList = new ArrayList<>();
    }

    /**
     * Class second constructor that instantiates a diary with a user and empty day
     * list.
     *
     * @param user
     */
    public Diary(User user) {
        this.user = user;
        this.dayList = new ArrayList<>();
    }

    /**
     * @return the diary user
     */
    public User getUser() {
        return user;
    }

    /**
     * @return the diary day list
     */
    public ArrayList<Day> getDayList() {
        return dayList;
    }

    /**
     * Puts in a map the total quantity of nutrients or not nutrients of all days in
     * the diary.
     *
     * @return the total quantity of nutrients or not nutrients of a user's diary
     */
    public Map<AllNutrientNonNutrient, Float> getSumValues() {
        sumValues = new HashMap<>() {
        };
        for (var nutrient : AllNutrientNonNutrient.values()) {
            float sum = 0.0f;
            for (var day : dayList)
                sum += day.calculate(nutrient.getReferenceClass());
            if (sum > 0.0000001f && nutrient != AllNutrientNonNutrient.SUGAR
                    && nutrient != AllNutrientNonNutrient.SATURATED)
                sumValues.put(nutrient, sum);
        }
        return sumValues;
    }

    /**
     * Calculates the total calories of all days in the diary. Days have meals, that
     * have foods that have calories.
     *
     * @return all days total calories
     */
    public float getTotalCalories() {
        float calories = 0;
        for (var day : dayList)
            calories += day.getTotalCalories();
        return calories;
    }

    /**
     * Loads the diary of an user with his token. First checks if the diary you are
     * looking for is in local database and if it's not it checks the remote
     * database on Dropbox. If there isn't a diary associated with the user's token
     * it returns null.
     *
     * @param userToken associated with the diary you are looking for
     * @return user's diary you need
     */
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

    /**
     * Saves the diary in local database and in remote database on Dropbox
     */
    public void save() {
        Serialization s = new Serialization(DIR, user.generateToken() + ".dat");
        s.saveObject(this);
        DropboxAPI.uploadFile(new File(s.getFullPath()), DROPBOX_DIR);
    }

    /**
     * Resets the map that contains weight and the respective date by creating a new
     * map in which only the last entry of the old map is inserted. It also clear
     * the day list
     */
    public void reset() {
        Map.Entry lastEntry = user.getWeight().lastEntry();
        user.setWeight(new TreeMap<>() {
            {
                putAll(Map.ofEntries(lastEntry));
            }
        });
        dayList = new ArrayList<>();
        save();
    }

    /**
     * Finds the day you are looking for through the parameter. Returns it if it was
     * found, otherwise returns null.
     *
     * @param dayId is the day date you are searching as a string
     * @return day if it was found in the day list of diary
     */
    public Day findDayById(String dayId) {
        dayId = dayId.replace("-", "/");
        for (Day day : dayList) {
            if (Diary.stringToLocalDate(dayId).isEqual(day.getDate()))
                return day;
        }
        return null;
    }

    /**
     * Adds food to a specific day and in a specific meal. If the day is not in the
     * day list of the diary it creates one and adds food to meal.
     *
     * @param dayId    the day you want to add the food to
     * @param mealType the type of meal you want to add the food to
     * @param food     the food you want to add
     */
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

    /**
     * Converts string that contains a date given as a parameter to LocalDate.
     *
     * @param date as a string that has to be converted into Localdate
     * @return formatted date into LocalDate
     */
    public static LocalDate stringToLocalDate(String date) {
        String[] values = date.replace("-", "/").split("/");
        String formatted = "" + String.format("%02d", Integer.parseInt(values[0])) + "/"
                + String.format("%02d", Integer.parseInt(values[1])) + "/" + Integer.parseInt(values[2]);
        return LocalDate.parse(formatted, formatter);
    }

    /**
     * Adds water to a specific day in water list. If the day is not in the day list
     * of the diary it creates one and adds water to water list.
     *
     * @param dayId the day you want to add the water to
     * @param water the water you want to add
     */
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

    /**
     * Updates user's weight and corresponding date
     *
     * @param newWeight new user's weight that has to replace the old one
     * @param date      date corresponding to the user's new weight
     */
    public void updateWeight(float newWeight, LocalDate date) {
        user.getWeight().put(date, newWeight);
        save();
    }

    /**
     * First filters the diary and then calculate statistics regarding specific
     * statistic types given as a parameter. While doing this, exceptions can be
     * thrown if a particular method cannot be found, if a specified class object
     * cannot be instantiated and if an application tries to reflectively create an
     * instance, set or get a field, or invoke a method, but the currently executing
     * method does not have access to the definition of the specified class, field,
     * method or constructor.
     *
     * @param filters       used to filter diary
     * @param statisticType statistics you want to do on diary
     * @return statistics on filtered diary in a JSONObject
     * @throws NoSuchMethodException     if a method cannot be found
     * @throws InvocationTargetException this holds an exception thrown by an
     *                                   invoked method or constructor
     * @throws InstantiationException    when the method newInstance tries to create
     *                                   an instance of a class but the specified
     *                                   class object cannot be instantiated.
     * @throws IllegalAccessException    when the method tries to reflectively
     *                                   create an instance, set or get a field, or
     *                                   invoke a method, but the currently
     *                                   executing method does not have access to
     *                                   the definition of the specified class,
     *                                   field, method or constructor.
     */
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

    /**
     * Returns filtered diary by filters given as a parameter and by removing empty
     * days.
     *
     * @param filters used to filter diary
     * @return filtered diary
     */
    public Diary doFilter(ArrayList<Filter> filters) {
        for (var filter : filters)
            filter.filter(this);
        removeEmptyDays();
        return this;
    }

    /**
     * Removes a day if there are no meals and water
     */
    private void removeEmptyDays() {
        dayList.removeIf(day -> day.getMealList().size() == 0 && day.getWaterList().size() == 0);
    }

    /**
     * Puts the diary into a JSONObject and returns it
     *
     * @return JSONObject which contains diary
     */
    public JSONObject toJsonObject() {
        JSONObject toJsonObject = new JSONObject();
        toJsonObject.put("diary", this);
        return toJsonObject;
    }

}