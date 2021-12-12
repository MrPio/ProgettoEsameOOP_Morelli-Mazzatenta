package com.univpm.po.NutritionStats.controller;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.model.User;
import com.univpm.po.NutritionStats.service.MainService;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    final String ENDPOINT_EAN    = "/api/ean/";
    final String ENDPOINT_SIGNUP = "/signup";

    @RequestMapping(path = ENDPOINT_EAN+"{ean_code}", method = RequestMethod.GET)
    public ResponseEntity<Object> getInfoFromEan(@PathVariable("ean_code") String ean) {
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
                MainService.requestSignUp(new User(nickname,email,year,weight,height,diet,gender)),
                HttpStatus.OK);
    }
}
