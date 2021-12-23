package com.univpm.po.NutritionStats.controller;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.enums.*;
import com.univpm.po.NutritionStats.exception.ApiFoodNotFoundException;
import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.exception.UserAlreadyInDatabase;
import com.univpm.po.NutritionStats.exception.UserNotFound;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.User;
import com.univpm.po.NutritionStats.service.FilterManager;
import com.univpm.po.NutritionStats.service.MainService;
import com.univpm.po.NutritionStats.service.filter.Filter;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
public class Controller {
    final String ENDPOINT_FOOD = "/api/name/{food_name}";
    final String ENDPOINT_EAN = "/api/ean/{ean_code}";
    final String ENDPOINT_ADD_FOOD_BY_NAME = "/add/food/by_name";
    final String ENDPOINT_ADD_FOOD_BY_EAN = "/add/food/by_ean";
    final String ENDPOINT_ADD_WATER = "/add/water";
    final String ENDPOINT_DIARY_INFO = "/diary";
    final String ENDPOINT_DAY_INFO = "/diary/{day_id}";
    final String ENDPOINT_SIGNUP = "/signup";
    final String ENDPOINT_LOGIN = "/login";
    final String ENDPOINT_RESET = "/reset";
    final String ENDPOINT_UPDATE_WEIGHT = "/update_weight";
    final String ENDPOINT_STATS = "/stats";
    final String ENDPOINT_FILTERS = "/filters";

    MainService mainService = new MainService();

    @RequestMapping(path = "/")
    public ResponseEntity<Object> Welcome() {
        return new ResponseEntity<>(
                new JSONObject(Map.of("welcome", "Welcome this is Nutrition Stats, RESTful API, " +
                        "you can find the repository here:\r\n" +
                        "https://github.com/MrPio/ProgettoEsameOOP_Morelli-Mazzatenta")),
                HttpStatus.OK);
    }

    @RequestMapping(path = ENDPOINT_FOOD, method = RequestMethod.GET)
    public ResponseEntity<Object> getInfoFromFoodName(
            @PathVariable("food_name") String foodName,
            @RequestParam(value = "portion_weight", defaultValue = "100") Integer weight,
            @RequestParam(value = "unit_of_measure", defaultValue = "GR") Measure measure) {
        try {
            return new ResponseEntity<>(
                    EdamamNutritionAnalysisAPI.getFood(foodName, weight, measure),
                    HttpStatus.OK);
        } catch (ApiFoodNotFoundException e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage())), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(path = ENDPOINT_EAN, method = RequestMethod.GET)
    public ResponseEntity<Object> getInfoFromEan(
            @PathVariable("ean_code") Long eanCode) {
        try {
            return new ResponseEntity<>(
                    ChompBarcodeSearchAPI.getEanInfo(eanCode),
                    HttpStatus.OK);
        } catch (ApiFoodNotFoundException e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = ENDPOINT_ADD_FOOD_BY_NAME, method = RequestMethod.POST)
    public ResponseEntity<Object> requestAddFoodByName(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "day_id") String dayId,
            @RequestParam(value = "meal_type") MealType mealType,
            @RequestParam(value = "food_name") String foodName,
            @RequestParam(value = "portion_weight") Integer portionWeight,
            @RequestParam(value = "unit_of_measure ", defaultValue = "GR") Measure measureUnit) throws NoSuchMethodException {
        try {
            return mainService.requestAddFoodByName(token, dayId, mealType, foodName, portionWeight, measureUnit);
        } catch (ApiFoodNotFoundException e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage())), HttpStatus.BAD_REQUEST);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("result", "not found",
                    "message", e.getMessage(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = ENDPOINT_ADD_FOOD_BY_EAN, method = RequestMethod.POST)
    public ResponseEntity<Object> requestAddFoodByEan(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "day_id") String dayId,
            @RequestParam(value = "meal_type") MealType mealType,
            @RequestParam(value = "ean_code") Long eanCode,
            @RequestParam(value = "portion_weight") Integer portionWeight) throws NoSuchMethodException {
        try {
            return mainService.requestAddFoodByEan(token, dayId, mealType, eanCode, portionWeight);
        } catch (ApiFoodNotFoundException e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage())), HttpStatus.BAD_REQUEST);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("result", "not found",
                    "message", e.getMessage(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = ENDPOINT_ADD_WATER, method = RequestMethod.POST)
    public ResponseEntity<Object> requestAddWater(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "day_id") String dayId,
            @RequestParam(value = "portion_volume") Integer volume) throws NoSuchMethodException {
        try {
            return mainService.requestAddWater(token, dayId, volume);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("result", "not found",
                    "message", e.getMessage(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = ENDPOINT_DIARY_INFO, method = RequestMethod.GET)
    public ResponseEntity<Object> requestDiaryInfo(
            @RequestParam(value = "token") String token) throws NoSuchMethodException {
        try {
            return mainService.requestDiaryValues(token);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("result", "not found",
                    "message", e.getMessage(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = ENDPOINT_DAY_INFO, method = RequestMethod.GET)
    public ResponseEntity<Object> requestDayInfo(
            @PathVariable(value = "day_id") String dayId,
            @RequestParam(value = "token") String token) throws NoSuchMethodException {
        System.err.println(ENDPOINT_DAY_INFO);
        try {
            return mainService.requestDayValues(token, dayId);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("result", "not found",
                    "message", e.getMessage(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = ENDPOINT_SIGNUP, method = RequestMethod.POST)
    public ResponseEntity<Object> requestSignUp(
            @RequestParam(value = "nickname") String nickname,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "birth") String birth,
            @RequestParam(value = "weight") Integer weight,
            @RequestParam(value = "height") Integer height,
            @RequestParam("diet") Diet diet,
            @RequestParam(value = "gender") Gender gender) {
        try {
            return mainService.requestSignUp(new User(nickname, email, LocalDate.parse(birth, Diary.formatter), height, weight, diet, gender));
        } catch (UserAlreadyInDatabase e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = ENDPOINT_LOGIN, method = RequestMethod.GET)
    public ResponseEntity<Object> requestLogin(
            @RequestParam(value = "token") String token) throws NoSuchMethodException {
        System.err.println(ENDPOINT_LOGIN);
        try {
            return mainService.requestLogin(token);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("result", "not found",
                    "message", e.getMessage(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = ENDPOINT_RESET, method = RequestMethod.DELETE)
    public ResponseEntity<Object> requestReset(
            @RequestParam(value = "token") String token) throws NoSuchMethodException {
        try {
            return mainService.requestReset(token);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("result", "not found",
                    "message", e.getMessage(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = ENDPOINT_UPDATE_WEIGHT, method = RequestMethod.PUT)
    public ResponseEntity<Object> requestUpdateWeight(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "weight") Float weight,
            @RequestParam(value = "date", defaultValue = "null") String date) throws NoSuchMethodException {
        try {
            LocalDate dateFormatted;
            if (date.equals("null"))
                dateFormatted = LocalDate.now();
            else
                dateFormatted = LocalDate.parse(date, Diary.formatter);
            return mainService.requestUpdateWeight(token, weight, dateFormatted);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("result", "not found",
                    "message", e.getMessage(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = ENDPOINT_STATS, method = RequestMethod.GET)
    public ResponseEntity<Object> requestStats(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "type") StatisticType[] types,
            @RequestBody FilterManager filterManager) throws NoSuchMethodException {
        try {
            return mainService.requestStats(token, types, filterManager.getFiltersList());
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of(
                    "message", e.getMessage(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        } catch (EndDateBeforeStartDateException e) {
            return new ResponseEntity<>(new JSONObject(Map.of(
                    "message", e.getMessage())), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(path = ENDPOINT_FILTERS, method = RequestMethod.GET)
    public ResponseEntity<Object> requestFilter(
            @RequestParam(value = "token") String token,
            @RequestBody FilterManager filterManager) throws NoSuchMethodException {
        try {
            return mainService.requestFilters(token, filterManager.getFiltersList());
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of(
                    "message", e.getMessage(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        } catch (EndDateBeforeStartDateException e) {
            return new ResponseEntity<>(new JSONObject(Map.of(
                    "message", e.getMessage())), HttpStatus.BAD_REQUEST);
        }
    }
}
