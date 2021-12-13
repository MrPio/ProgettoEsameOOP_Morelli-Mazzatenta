package com.univpm.po.NutritionStats.controller;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.model.Meal;
import com.univpm.po.NutritionStats.model.User;
import com.univpm.po.NutritionStats.service.MainService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    final String ENDPOINT_EAN    = "/api/ean/";
    final String ENDPOINT_SIGNUP = "/signup";
    final String ENDPOINT_LOGIN = "/login";
    final String ENDPOINT_TODAY_VALUES = "/diary/";
    final String ENDPOINT_ADD_FOOD="/add/food";

    @RequestMapping(path="/")
    public ResponseEntity<Object> Welcome() {
        return new ResponseEntity<>(
                new JSONObject(Map.of("welcome","Welcome this is Nutrition Stats, RESTful API, " +
                        "you can find the repository here:\r\n" +
                        "https://github.com/MrPio/ProgettoEsameOOP_Morelli-Mazzatenta")),
                HttpStatus.OK);
    }

    @RequestMapping(path = ENDPOINT_EAN+"{ean_code}", method = RequestMethod.GET)
    public ResponseEntity<Object> getInfoFromEan(
            @PathVariable("ean_code") String ean) {
        return new ResponseEntity<>(
                ChompBarcodeSearchAPI.getEanInfo(ean),
                HttpStatus.OK);
    }

    @RequestMapping(path = ENDPOINT_SIGNUP, method = RequestMethod.POST)
    public ResponseEntity<Object> requestSignUp(
            @RequestParam("nickname")String nickname,
            @RequestParam("email")String email,
            @RequestParam("year")Integer year,
            @RequestParam("weight")Integer weight,
            @RequestParam("height")Integer height,
            @RequestParam("diet")User.Diet diet,
            @RequestParam("gender") User.Gender gender) {
        return new ResponseEntity<>(
                MainService.requestSignUp(new User(nickname,email,year,height,weight,diet,gender)),
                HttpStatus.OK);
    }

    @RequestMapping(path = ENDPOINT_LOGIN, method = RequestMethod.GET)
    public ResponseEntity<Object> requestSignUp(
            @RequestParam("token")String token) {
        return new ResponseEntity<>(
                MainService.requestLogin(token),
                HttpStatus.OK);
    }

    @RequestMapping(path = ENDPOINT_TODAY_VALUES+"{day_id}", method = RequestMethod.GET)
    public ResponseEntity<Object> requestTodayValue(
            @PathVariable("day_id") String dayId,
            @RequestParam("token")String token) {
        return new ResponseEntity<>(
                MainService.requestTodayValues(token,dayId),
                HttpStatus.OK);
    }

    @RequestMapping(path = ENDPOINT_ADD_FOOD, method = RequestMethod.POST)
    public ResponseEntity<Object> requestTodayValue(
            @RequestParam("token") String token,
            @RequestParam("day_id")String dayId,
            @RequestParam("meal_type")Meal.MealType mealType,
            @RequestParam(value = "food_name",defaultValue = "")String foodName,
            @RequestParam(value = "ean_code",defaultValue = "")String eanCode) {
        return new ResponseEntity<>(
                MainService.requestAddFood(token,dayId,mealType,foodName,eanCode),
                HttpStatus.OK);
    }
}
