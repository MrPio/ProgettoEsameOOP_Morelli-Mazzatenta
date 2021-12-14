package com.univpm.po.NutritionStats.api;

import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.nutrient.*;
import com.univpm.po.NutritionStats.utility.InputOutputImpl;
import com.univpm.po.NutritionStats.utility.SerializationImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EdamamNutritionAnalysisAPI {
    final static String DIR = "api_response/edamam/nutrition/";
    final static String DROPBOX_DIR = "/api_response/edamam/nutrition/";
    final static String APP_KEY = "c11b62ab316bba79e2f918c16befa7f7";
    final static String APP_ID = "a3c59b8d";
    final static String URL = "https://api.edamam.com/api/nutrition-data?app_id=" + APP_ID;

    public static JSONObject getFoodInfo(String foodName) {
        foodName = foodName.replace(" ", "%20");
        //Check if I already have the information needed:
        //in local database
        InputOutputImpl inputOutputEan = new InputOutputImpl(DIR, foodName + ".dat");
        if (inputOutputEan.existFile()) {
            SerializationImpl serializationResult = new SerializationImpl(DIR, foodName + ".dat");
            return (JSONObject) serializationResult.loadObject();
        }
        //in remote database
        if (DropboxAPI.getFilesInFolder(DROPBOX_DIR).contains(foodName + ".dat")) {
            DropboxAPI.downloadFile(DROPBOX_DIR + foodName + ".dat", DIR + foodName + ".dat");
            SerializationImpl serializationResult = new SerializationImpl(DIR, foodName + ".dat");
            return (JSONObject) serializationResult.loadObject();
        }

        JSONObject result = null;

        HttpURLConnection conn = null;
        try {
            //send request
            conn = (HttpURLConnection) new URL(
                    EdamamNutritionAnalysisAPI.URL + "&app_key=" + APP_KEY + "&ingr=" + foodName).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            //read json response
            StringBuilder data = new StringBuilder();
            String line = "";
            try (InputStream in = conn.getInputStream();) {
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));
                while ((line = buf.readLine()) != null)
                    data.append(line);
            }
            result = (JSONObject) JSONValue.parseWithException(data.toString());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }

        //store the result to avoid different future calls on this same request
        SerializationImpl serializationResult = new SerializationImpl(DIR, foodName + ".dat");
        serializationResult.saveObject(result);
        DropboxAPI.uploadFile(new File(serializationResult.getFullPath()), DROPBOX_DIR);
        return result;
    }

    public static Food getFood(String foodName, int portionWeight, Measure measureUnit) {
        Food foodResult;
        JSONObject foodInfo = getFoodInfo(foodName + " " + portionWeight + measureUnit.name());

        //creazione food
        JSONArray healthLabels = (JSONArray) foodInfo.get("healthLabels");
        Diet diet = null;
        boolean pescetarian = false, vegetarian = false, vegan = false;
        for (Object label : healthLabels) {
            switch (label.toString()) {
                case "PESCATARIAN":
                    pescetarian = true;
                    break;
                case "VEGETARIAN":
                    vegetarian = true;
                    break;
                case "VEGAN":
                    vegan = true;
                    break;
            }
        }
        if (vegan)
            diet = Diet.VEGAN;
        else if (vegetarian)
            diet = Diet.VEGETARIAN;
        else if (pescetarian)
            diet = Diet.PESCATARIAN;
        else
            diet = Diet.CLASSIC;
        int newWeight = (int)((double)foodInfo.get("totalWeight"));
        foodResult = new Food(foodName, newWeight, Measure.GR,diet);

        JSONObject foodNutrientsInfo = (JSONObject) foodInfo.get("totalNutrients");
        //NUTRIENT
        foodResult.addNutrient(new Carbohydrate(labelToValue(foodNutrientsInfo, "CHOCDF"), labelToValue(foodNutrientsInfo, "SUGAR")));
        foodResult.addNutrient(new Protein(labelToValue(foodNutrientsInfo, "PROCNT")));
        foodResult.addNutrient(new Lipid(labelToValue(foodNutrientsInfo, "FAT"), labelToValue(foodNutrientsInfo, "FASAT")));
        foodResult.addNutrient(new VitaminA(labelToValue(foodNutrientsInfo, "VITA_RAE")));
        foodResult.addNutrient(new VitaminC(labelToValue(foodNutrientsInfo, "VITC")));
        foodResult.addNutrient(new Calcium(labelToValue(foodNutrientsInfo, "CA")));
        foodResult.addNutrient(new Sodium(labelToValue(foodNutrientsInfo, "NA")));
        foodResult.addNutrient(new Potassium(labelToValue(foodNutrientsInfo, "K")));
        foodResult.addNutrient(new Iron(labelToValue(foodNutrientsInfo, "FE")));

        //NON NUTRIENT
        foodResult.addNotNutrient(new WaterFromFood(labelToValue(foodNutrientsInfo, "WATER")));
        foodResult.addNotNutrient(new Fiber(labelToValue(foodNutrientsInfo, "FIBTG")));

        return foodResult;
    }

    private static float labelToValue(JSONObject foodInfo, String label) {
        JSONObject infoSector = (JSONObject) foodInfo.get(label);
        float scaleFactor = 1.0f;
        switch (infoSector.get("unit").toString()) {
            case "g":
                scaleFactor = 1.0f;
                break;
            case "mg":
                scaleFactor = 0.001f;
                break;
            case "µg":
                scaleFactor = 0.000001f;
                break;
        }
        return (float) ((double) infoSector.get("quantity")) * scaleFactor;
    }
}
