package com.univpm.po.NutritionStats.controller;

import com.univpm.po.NutritionStats.model.TestPerson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("ciao")
    public TestPerson getPerson(@RequestParam(name="param1",defaultValue = "Caio1")String surname) {
        return new TestPerson("tizio",surname);
    }
}
