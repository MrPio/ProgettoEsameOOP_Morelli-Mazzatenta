package com.univpm.po.NutritionStats.controller;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @RequestMapping(path = "/api/ean/{ean_code}", method = RequestMethod.GET)
    public ResponseEntity<Object> welcomeScreen(@PathVariable("ean_code") String ean) {
        return new ResponseEntity<>(
                ChompBarcodeSearchAPI.getEanInfo(ean),
                HttpStatus.OK);
    }
}
