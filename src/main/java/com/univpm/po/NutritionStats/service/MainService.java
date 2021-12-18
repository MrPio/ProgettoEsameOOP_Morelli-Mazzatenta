package com.univpm.po.NutritionStats.service;

import com.sun.jdi.Method;
import com.univpm.po.NutritionStats.exception.ApiFoodNotFoundException;
import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.exception.UserAlreadyInDatabase;
import com.univpm.po.NutritionStats.model.*;
import com.univpm.po.NutritionStats.model.nutrient.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainService {

    interface Interface {
        public void myMethod(Object... component );
    }


    private ArrayList<Class<?>> classList = new ArrayList<>() {{
        add(Carbohydrate.class);
        add(Lipid.class);
        add(Protein.class);
        add(Water.class);
        add(VitaminA.class);
        add(VitaminC.class);
        add(Sodium.class);
        add(Calcium.class);
        add(Potassium.class);
        add(Iron.class);
        add(Fiber.class);
    }};


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

    public ResponseEntity<Object> requestAddWater(String token, String dayId, MealType mealType, int volume) {
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
            response = requestedDiary.toJsonObject();
            Runnable toJsonObject = requestedDiary::toJsonObject;
            //DiaryInterface di=Diary::addWater;
            toJsonObject.run();
            response.put("result", "user found");
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
            throw new UserAlreadyInDatabase(user.getEmail(), user.generateToken());

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
            response.put("birth", requestedUser.getYearOfBirth());
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

    public ResponseEntity<Object> requestUpgradeWeight(String token, float weight) {
        HashMap<String, Object> response = new HashMap<>();
        HttpStatus httpStatus;
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            requestedDiary.getUser().getWeight().put(LocalDate.now(),weight);
            requestedDiary.save();
            response.put("result", "success");
            httpStatus = HttpStatus.OK;
        } else {
            response.put("result", "user not found");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }


    private HashMap<String, Object> dayToJsonObject(Day day) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("day_id", day.calculateDayId());
        response.put("calories", day.calculateCalories());
        for (Class<?> c : classList)
            response.put(c.getSimpleName().toLowerCase(), day.calculate(c));

/*      response.put("water", day.calculateWater());
        response.put("carbohydrates", day.calculateCarbohydrates());
        response.put("proteins", day.calculateProteins());
        response.put("lipids", day.calculateLipids());
        response.put("calcium", day.calculateCalcium());
        response.put("fiber", day.calculateFiber());
        response.put("iron", day.calculateIron());
        response.put("potassium", day.calculatePotassium());
        response.put("sodium", day.calculateSodium());
        response.put("vitamin_a", day.calculateVitaminA());
        response.put("vitamin_c", day.calculateVitaminC());*/
        return response;
    }

    private ResponseEntity<Object> workWithDiary(String token, Runnable func){
        JSONObject response = new JSONObject();
        HttpStatus httpStatus = null;
        Diary requestedDiary = Diary.load(token);
        if (requestedDiary != null) {
            //response = func.run();
            response.put("result", "user found");
            httpStatus = HttpStatus.OK;
        } else {
            response.put("result", "user not found");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }
}
