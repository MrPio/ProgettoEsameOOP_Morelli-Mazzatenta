package com.univpm.po.NutritionStats.api;

import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.exception.ApiFoodNotFoundException;
import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.nutrient.*;
import com.univpm.po.NutritionStats.utility.InputOutput;
import com.univpm.po.NutritionStats.utility.Serialization;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * {@code EdamamNutritionAnalysisAPI} class can handle communication with Edamam Nutrition api. Its main purpose is the
 * conversion from the JSONObject given by Edamam Nutrition api to an instance of {@link Food} containing only the
 * information needed by this application.
 *
 * @author Valerio Morelli
 */
public class EdamamNutritionAnalysisAPI {
    final static String DIR = "api_response/edamam/nutrition/";
    final static String DROPBOX_DIR = "/api_response/edamam/nutrition/";
    final static String APP_KEY = "c11b62ab316bba79e2f918c16befa7f7";
    final static String APP_ID = "a3c59b8d";
    final static String URL = "https://api.edamam.com/api/nutrition-data?app_id=" + APP_ID;

    /**
     * <strong>This method is used to store the response of Edamam api inside an instance of {@link JSONObject}.</strong>
     *
     * @param foodName the requested food name.
     * @return an instance of {@link JSONObject} containing the response of Edamam api.
     * @throws ApiFoodNotFoundException when the provided food name cannot be found by Chomp api.
     */
    public static JSONObject getFoodInfo(String foodName) throws ApiFoodNotFoundException {
        foodName = foodName.replace(" ", "%20");
        JSONObject result = null;
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(
                    EdamamNutritionAnalysisAPI.URL + "&app_key=" + APP_KEY + "&ingr=" + foodName).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            StringBuilder data = new StringBuilder();
            String line = "";
            try (InputStream in = conn.getInputStream();) {
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));
                while ((line = buf.readLine()) != null)
                    data.append(line);
            }
            result = (JSONObject) JSONValue.parseWithException(data.toString());
            if ((double) result.get("totalWeight") == 0.0d)
                throw new ApiFoodNotFoundException(foodName);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    /**
     * <Strong>This method is used to get an instance of {@link Food} containing all the needed information about requested food name.</Strong>
     * <p>
     * <b>NOTE</b>: <i>if a previous request with the same ean-code was made, this method avoids to make the same request;
     * as a matter of fact it first looks for a serialized instance of {@link Food} (with the same name) locally and
     * {@link DropboxAPI#getFilesInFolder(String) remotely} , then, if the search was a success, it loads that file inside
     * an instance of {@link Food}, and returns it, without even connect with Edamam api.</i>
     * </p>
     *
     * @param foodName      the requested food name.
     * @param portionWeight the weight of the portion.
     * @param measureUnit   a value of {@link Measure} that indicates the unity of measure with which the value of the portion weight is expressed.
     * @return an instance of {@link Food} containing all the information filtered from the response of Edamam api.
     * @throws ApiFoodNotFoundException when the provided ean-code cannot be found by Chomp api.
     */
    public static Food getFood(String foodName, int portionWeight, Measure measureUnit) throws ApiFoodNotFoundException {

        InputOutput inputOutputEan = new InputOutput(DIR, foodName + ".dat");
        if (inputOutputEan.existFile()) {
            Serialization serializationResult = new Serialization(DIR, foodName + ".dat");
            Food result = (Food) serializationResult.loadObject();
            result.newPortionWeight(portionWeight);
            return result;
        }
        if (DropboxAPI.getFilesInFolder(DROPBOX_DIR).contains(foodName + ".dat")) {
            DropboxAPI.downloadFile(DROPBOX_DIR + foodName + ".dat", DIR + foodName + ".dat");
            Serialization serializationResult = new Serialization(DIR, foodName + ".dat");
            Food result = (Food) serializationResult.loadObject();
            result.newPortionWeight(portionWeight);
            return result;
        }

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
        int newWeight = (int) ((double) foodInfo.get("totalWeight"));
        foodResult = new Food(foodName, newWeight, Measure.GR, diet);

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

        //store the result to avoid different future calls on this same request
        Serialization serializationResult = new Serialization(DIR, foodName + ".dat");
        serializationResult.saveObject(foodResult);
        DropboxAPI.uploadFile(new File(serializationResult.getFullPath()), DROPBOX_DIR);

        return foodResult;
    }

    /**
     * <strong>This method converts float values expressed in <i>g, mg, µg</i> in a float value expressed in <i>g</i></strong>
     *
     * @param foodInfo the {@link JSONObject} containing all the information about a food name.
     * @param label    the <b>key</b> value to search inside the {@link JSONObject}.
     * @return the float value expressed in <i>g</i>
     */
    private static float labelToValue(JSONObject foodInfo, String label) {
        if (!foodInfo.containsKey(label))
            return 0.0f;
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
