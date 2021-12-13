package com.univpm.po.NutritionStats.service;

import com.univpm.po.NutritionStats.api.DropboxAPI;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.User;
import com.univpm.po.NutritionStats.utility.InputOutputImpl;
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

    public static JSONObject requestLogin(String token) {
        HashMap<String,Object> response=new HashMap<>();
        Diary requestedDiary=Diary.load(token);
        if(requestedDiary!=null){
            User requestedUser=requestedDiary.getUser();
            response.put("result","found");
            response.put("nickname",requestedUser.getNickname());
            response.put("email",requestedUser.getEmail());
            response.put("year",requestedUser.getYearOfBirth());
            response.put("weight",requestedUser.getWeight());
            response.put("height",requestedUser.getHeight());
            response.put("diet",requestedUser.getDiet());
            response.put("gender",requestedUser.getGender());
        }
        else
            response.put("result","not found");
        return new JSONObject(response);
    }
}
