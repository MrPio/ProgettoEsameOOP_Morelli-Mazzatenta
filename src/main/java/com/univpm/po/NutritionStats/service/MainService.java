package com.univpm.po.NutritionStats.service;

import com.univpm.po.NutritionStats.exception.ApiFoodNotFoundException;
import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.exception.UserAlreadyInDatabase;
import com.univpm.po.NutritionStats.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainService {

    public ResponseEntity<Object> requestAddFoodByName(String token, String dayId, MealType mealType, String foodName,
                                                       int portionWeight, Measure measureUnit) throws ApiFoodNotFoundException {
        HashMap<String, Object> response = new HashMap<>();
        HttpStatus httpStatus;
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            Food foodToAdd = EdamamNutritionAnalysisAPI.getFood(foodName, portionWeight, measureUnit);
            requestedDiary.addFood(dayId, mealType, foodToAdd);
            response.put("result", "success");
            httpStatus = HttpStatus.OK;
        } else {
            response.put("result", "user not found");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }

    public ResponseEntity<Object> requestAddFoodByEan(String token, String dayId, MealType mealType, long eanCode,
                                                      int portionWeight) throws ApiFoodNotFoundException {
        HashMap<String, Object> response = new HashMap<>();
        HttpStatus httpStatus;
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            Food foodToAdd = ChompBarcodeSearchAPI.getFood(eanCode, portionWeight);
            requestedDiary.addFood(dayId, mealType, foodToAdd);
            response.put("result", "success");
            httpStatus = HttpStatus.OK;
        } else {
            response.put("result", "user not found");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }

    public ResponseEntity<Object> requestAddWater(String token, String dayId, MealType mealType,
                                                  int volume, Measure measureUnit) {
        HashMap<String, Object> response = new HashMap<>();
        HttpStatus httpStatus;
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            Water waterToAdd = new Water(volume);
            requestedDiary.addWater(dayId, mealType, waterToAdd);
            response.put("result", "success");
            httpStatus = HttpStatus.OK;
        } else {
            response.put("result", "user not found");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }

    public ResponseEntity<Object> requestDiaryValues(String token) {
        JSONObject response = new JSONObject();
        HttpStatus httpStatus = null;
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            //JSONArray resultAllDay=new JSONArray();
            response = requestedDiary.toJsonObject();
            response.put("result", "user found");
            /*for(Day day:requestedDiary.getDayList())
                resultAllDay.add(new JSONObject(dayToJsonObject(day)));
            response.put("day_list",resultAllDay);*/
            httpStatus = HttpStatus.OK;
        } else {
            response.put("result", "user not found");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }

    public ResponseEntity<Object> requestDayValues(String token, String dayId) {
        HashMap<String, Object> response = new HashMap<>();
        HttpStatus httpStatus = null;
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            Day requestedDay = requestedDiary.findDayById(dayId);
            if (requestedDay != null) {
                response.put("result", "day found");
                response = dayToJsonObject(requestedDay);
                httpStatus = HttpStatus.OK;
            } else {
                response.put("result", "day not found");
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } else {
            response.put("result", "user not found");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }

    public ResponseEntity<Object> requestSignUp(User user) throws UserAlreadyInDatabase {
        HashMap<String, String> response = new HashMap<>();
        //check if User is already in database
        if (Diary.load(user.generateToken()) != null)
            throw new UserAlreadyInDatabase(user.getEmail(),user.generateToken());

        response.put("email", user.getEmail());
        response.put("token", user.generateToken());

        //store the new user in local and remote database
        Diary newDiary = new Diary(user, new ArrayList<>());
        newDiary.save();
        return new ResponseEntity<>(new JSONObject(response), HttpStatus.OK);
    }

    public ResponseEntity<Object> requestLogin(String token) {
        HashMap<String, Object> response = new HashMap<>();
        HttpStatus httpStatus = null;
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
            httpStatus = HttpStatus.OK;
        } else {
            response.put("result", "not found");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }

    private HashMap<String, Object> dayToJsonObject(Day day) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("day_id", day.getDayId());
        response.put("calories", day.calculateCalories());
        response.put("water", day.calculateWater());
        response.put("carbohydrates", day.calculateCarbohydrates());
        response.put("proteins", day.calculateProteins());
        response.put("lipids", day.calculateLipids());
        return response;
    }
}
