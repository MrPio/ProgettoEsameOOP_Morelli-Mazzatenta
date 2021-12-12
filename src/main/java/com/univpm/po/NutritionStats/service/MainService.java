package com.univpm.po.NutritionStats.service;

import com.univpm.po.NutritionStats.model.User;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class MainService {
    public static JSONObject requestSignUp(String userEmail){
        HashMap<String,String> response=new HashMap<>();
        response.put("email",userEmail);
        response.put("token",new User(userEmail).generateToken());
        return new JSONObject(response);
    }
}
