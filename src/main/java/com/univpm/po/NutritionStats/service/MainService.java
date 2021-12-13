package com.univpm.po.NutritionStats.service;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.api.DropboxAPI;
import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.model.*;
import com.univpm.po.NutritionStats.utility.InputOutputImpl;
import com.univpm.po.NutritionStats.utility.SerializationImpl;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainService {
    public static JSONObject requestSignUp(User user) {
        HashMap<String, String> response = new HashMap<>();
        response.put("email", user.getEmail());
        response.put("token", user.generateToken());

        //store the new user in local and remote database
        Diary newDiary = new Diary(user, new ArrayList<>());
        newDiary.save();
        return new JSONObject(response);
    }

    public static JSONObject requestLogin(String token) {
        HashMap<String, Object> response = new HashMap<>();
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            User requestedUser = requestedDiary.getUser();
            response.put("result", "found");
            response.put("nickname", requestedUser.getNickname());
            response.put("email", requestedUser.getEmail());
            response.put("year", requestedUser.getYearOfBirth());
            response.put("weight", requestedUser.getWeight());
            response.put("height", requestedUser.getHeight());
            response.put("diet", requestedUser.getDiet());
            response.put("gender", requestedUser.getGender());
        } else
            response.put("result", "not found");
        return new JSONObject(response);
    }

    public static JSONObject requestTodayValues(String token, String dayId) {
        HashMap<String, Object> response = new HashMap<>();
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            Day requestedDay = requestedDiary.findDayById(dayId);
            if (requestedDay != null) {
                response.put("result", "found");
                response.put("calories", requestedDay.calculateDayCalories());
                response.put("water", requestedDay.calculateDayWater());
                response.put("carbohydrates", requestedDay.calculateDayCarbohydrates());
                response.put("proteins", requestedDay.calculateDayProteins());
                response.put("lipids", requestedDay.calculateDayLipids());
            } else
                response.put("result", "day not found");
        } else
            response.put("result", "user not found");
        return new JSONObject(response);
    }

    public static JSONObject requestAddFood(String token, String dayId, Meal.MealType mealType, String foodName, String eanCode) {
        HashMap<String, Object> response = new HashMap<>();
        Food foodToAdd = null;
        if (!foodName.equals("") && eanCode.equals(""))
            foodToAdd = EdamamNutritionAnalysisAPI.getFood(foodName);
        else if (foodName.equals("") && !eanCode.equals(""))
            foodToAdd = ChompBarcodeSearchAPI.getFood(eanCode);
        if (foodToAdd == null) {
            response.put("result", "invalid params");
            return new JSONObject(response);
        }

        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null)
            requestedDiary.addFood(dayId, mealType, foodToAdd);
        else
            response.put("result", "user not found");
        return new JSONObject(response);
    }
}
