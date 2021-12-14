package com.univpm.po.NutritionStats.controller;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Gender;
import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.User;
import com.univpm.po.NutritionStats.service.MainService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {
    final String ENDPOINT_FOOD = "/api/name/{food_name}";
    final String ENDPOINT_EAN = "/api/ean/{ean_code}";
    final String ENDPOINT_ADD_FOOD_BY_NAME = "/add/food/by_name";
    final String ENDPOINT_ADD_FOOD_BY_EAN = "/add/food/by_ean";
    final String ENDPOINT_ADD_WATER = "/add/water";
    final String ENDPOINT_DIARY_INFO = "/diary/";
    final String ENDPOINT_TODAY_INFO = "/diary/{day_id}";
    final String ENDPOINT_SIGNUP = "/signup";
    final String ENDPOINT_LOGIN = "/login";

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
            @PathVariable("food_name") String foodName) {
        return new ResponseEntity<>(
                EdamamNutritionAnalysisAPI.getFoodInfo(foodName),
                HttpStatus.OK);
    }


    @RequestMapping(path = ENDPOINT_EAN, method = RequestMethod.GET)
    public ResponseEntity<Object> getInfoFromEan(
            @PathVariable("ean_code") String eanCode) {
        return new ResponseEntity<>(
                ChompBarcodeSearchAPI.getEanInfo(eanCode),
                HttpStatus.OK);
    }
    //TODO substitute Service return type with ResponseEntity<Object>


    @RequestMapping(path = ENDPOINT_ADD_FOOD_BY_NAME, method = RequestMethod.POST)
    public ResponseEntity<Object> requestAddFoodByName(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "day_id") String dayId,
            @RequestParam(value = "meal_type") MealType mealType,
            @RequestParam(value = "food_name") String foodName,
            @RequestParam(value = "portion_weight") Integer portionWeight,
            @RequestParam(value = "unit_of_measure ", defaultValue = "GR") Measure measureUnit) {
        return MainService.requestAddFoodByName(token, dayId, mealType, foodName, portionWeight, measureUnit);
    }

    @RequestMapping(path = ENDPOINT_ADD_FOOD_BY_EAN, method = RequestMethod.POST)
    public ResponseEntity<Object> requestAddFoodByEan(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "day_id") String dayId,
            @RequestParam(value = "meal_type") MealType mealType,
            @RequestParam(value = "ean_code") String eanCode,
            @RequestParam(value = "portion_weight") Integer portionWeight) {
        return MainService.requestAddFoodByEan(token, dayId, mealType, eanCode, portionWeight);
    }

    @RequestMapping(path = ENDPOINT_ADD_WATER, method = RequestMethod.POST)
    public ResponseEntity<Object> requestAddWater(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "day_id") String dayId,
            @RequestParam(value = "meal_type") MealType mealType,
            @RequestParam(value = "portion_volume") Integer volume,
            @RequestParam(value = "measure ", defaultValue = "ML") Measure measure) {
        return MainService.requestAddWater(token, dayId, mealType, volume, measure);
    }

    @RequestMapping(path = ENDPOINT_TODAY_INFO, method = RequestMethod.GET)
    public ResponseEntity<Object> requestTodayInfo(
            @PathVariable(value = "day_id") String dayId,
            @RequestParam(value = "token") String token) {
        return new ResponseEntity<>(
                MainService.requestTodayValues(token, dayId),
                HttpStatus.OK);
    }

    @RequestMapping(path = ENDPOINT_SIGNUP, method = RequestMethod.POST)
    public ResponseEntity<Object> requestSignUp(
            @RequestParam(value = "nickname") String nickname,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "weight") Integer weight,
            @RequestParam(value = "height") Integer height,
            @RequestParam("diet") Diet diet,
            @RequestParam(value = "gender") Gender gender) {
        return new ResponseEntity<>(
                MainService.requestSignUp(new User(nickname, email, year, height, weight, diet, gender)),
                HttpStatus.OK);
    }

    @RequestMapping(path = ENDPOINT_LOGIN, method = RequestMethod.GET)
    public ResponseEntity<Object> requestLogin(
            @RequestParam(value = "token") String token) {
        return new ResponseEntity<>(
                MainService.requestLogin(token),
                HttpStatus.OK);
    }


}
