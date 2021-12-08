package com.univpm.po.NutritionStats.controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.univpm.po.NutritionStats.model.TestPerson;

@RestController
public class TestController {	
	@GetMapping("ciao")
	public TestPerson getPerson(@RequestParam(name="param1",defaultValue = "Caio1")String surname) {
		return new TestPerson("tizio",surname);
	}	
}