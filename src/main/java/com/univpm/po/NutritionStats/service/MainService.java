package com.univpm.po.NutritionStats.service;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.*;
import com.univpm.po.NutritionStats.model.nutrient.Nutrient;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
                response.put("calories", requestedDay.calculateCalories());
                response.put("water", requestedDay.calculateWater());
                response.put("carbohydrates", requestedDay.calculateCarbohydrates());
                response.put("proteins", requestedDay.calculateProteins());
                response.put("lipids", requestedDay.calculateLipids());
            } else
                response.put("result", "day not found");
        } else
            response.put("result", "user not found");
        return new JSONObject(response);
    }

    public static ResponseEntity<Object> requestAddFoodByName(String token, String dayId, MealType mealType, String foodName,
                                              int portionWeight, Measure measureUnit) {
        HashMap<String, Object> response = new HashMap<>();
        HttpStatus httpStatus;
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            Food foodToAdd=EdamamNutritionAnalysisAPI.getFood(foodName,portionWeight,measureUnit);
            requestedDiary.addFood(dayId, mealType, foodToAdd);
            response.put("result","success");
            httpStatus=HttpStatus.OK;
        }
        else {
            response.put("result", "user not found");
            httpStatus=HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }

    public static ResponseEntity<Object> requestAddFoodByEan(String token, String dayId, MealType mealType, String eanCode,
                                             int portionWeight) {
        HashMap<String, Object> response = new HashMap<>();
        HttpStatus httpStatus;
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            Food foodToAdd=ChompBarcodeSearchAPI.getFood(eanCode,portionWeight);
            requestedDiary.addFood(dayId, mealType, foodToAdd);
            response.put("result","success");
            httpStatus=HttpStatus.OK;
        }
        else {
            response.put("result", "user not found");
            httpStatus=HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }

    public static ResponseEntity<Object> requestAddWater(String token, String dayId, MealType mealType,
                                                         int volume,Measure measureUnit) {
        HashMap<String, Object> response = new HashMap<>();
        HttpStatus httpStatus;
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            Water waterToAdd=new Water(volume);
            requestedDiary.addWater(dayId,mealType,waterToAdd);
            response.put("result","success");
            httpStatus=HttpStatus.OK;
        }
        else {
            response.put("result", "user not found");
            httpStatus=HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }
}
