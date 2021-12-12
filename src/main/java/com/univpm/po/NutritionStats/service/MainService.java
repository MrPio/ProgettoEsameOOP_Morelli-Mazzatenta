package com.univpm.po.NutritionStats.service;

import com.univpm.po.NutritionStats.api.DropboxAPI;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.User;
import com.univpm.po.NutritionStats.utility.SerializationImpl;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainService {
    public static JSONObject requestSignUp(User user){
        HashMap<String,String> response=new HashMap<>();
        response.put("email", user.getEmail());
        response.put("token", user.generateToken());

        //store the new user in local and remote database
        Diary newDiary=new Diary(user,new ArrayList<>());
        newDiary.save();
        return new JSONObject(response);
    }
}
