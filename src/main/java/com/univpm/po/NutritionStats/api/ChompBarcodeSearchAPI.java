package com.univpm.po.NutritionStats.api;

import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.nutrient.*;
import com.univpm.po.NutritionStats.utility.InputOutputImpl;
import com.univpm.po.NutritionStats.utility.SerializationImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class ChompBarcodeSearchAPI {
    final static String DIR = "api_response/chomp/";
    final static String DROPBOX_DIR = "/api_response/chomp/";
    final static String API_KEY = "AzytZXSqpb3nMitJ";
    final static String URL = "https://chompthis.com/api/v2/food/branded/barcode.php?api_key=" + API_KEY;

    public static JSONObject getEanInfo(String ean) {
        //Check if I already have the information needed:
        //in local database
        InputOutputImpl inputOutputEan = new InputOutputImpl(DIR, ean + ".dat");
        if (inputOutputEan.existFile()) {
            SerializationImpl serializationResult = new SerializationImpl(DIR, ean + ".dat");
            return (JSONObject) serializationResult.loadObject();
        }
        //in remote database
        if (DropboxAPI.getFilesInFolder(DROPBOX_DIR).contains(ean + ".dat")) {
            DropboxAPI.downloadFile(DROPBOX_DIR + ean + ".dat", DIR + ean + ".dat");
            SerializationImpl serializationResult = new SerializationImpl(DIR, ean + ".dat");
            return (JSONObject) serializationResult.loadObject();
        }

        JSONObject result = null;

        String url = ChompBarcodeSearchAPI.URL + "&code=" + ean;
        RestTemplate rt = new RestTemplate();
        JSONParser parser = new JSONParser();
        try {
            result = (JSONObject) parser.parse(rt.getForObject(url, String.class));
        } catch (HttpClientErrorException e) {
            System.out.println(e.getStatusCode());
            try {
                result = (JSONObject) parser.parse("{\"result\":\"404 not found\"}");
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //store the result to avoid different future calls on this same request
        SerializationImpl serializationResult = new SerializationImpl(DIR, ean + ".dat");
        serializationResult.saveObject(result);
        DropboxAPI.uploadFile(new File(serializationResult.getFullPath()), DROPBOX_DIR);
        return result;
    }

    public static Food getFood(String eanCode, int portionWeight) {
        Food foodResult;
        JSONObject foodInfo = (JSONObject) ((JSONArray) getEanInfo(eanCode).get("items")).get(0);

        //creazione food
        JSONObject dietLabels = (JSONObject) (foodInfo.get("diet_labels"));
        Diet diet = null;

        if (((JSONObject)dietLabels.get("vegan")).get("is_compatible")==null)
            diet = Diet.VEGAN;
        else if ((boolean) ((JSONObject)dietLabels.get("vegan")).get("is_compatible"))
            diet = Diet.VEGAN;

        else if (((JSONObject)dietLabels.get("vegetarian")).get("is_compatible")==null)
            diet = Diet.VEGETARIAN;
        else if ((boolean)((JSONObject)dietLabels.get("vegetarian")).get("is_compatible"))
            diet = Diet.VEGETARIAN;

        /*else if (pescetarian) TODO pescetarian
            diet = Diet.PESCATARIAN;*/
        else
            diet = Diet.CLASSIC;

        int newWeight = portionWeight;
        foodResult = new Food(foodInfo.get("name").toString(), newWeight, Measure.GR, diet);

        JSONArray foodNutrientsInfo = (JSONArray) foodInfo.get("nutrients");
        float[] nutritionValues=new float[20];
        for (Object label : foodNutrientsInfo) {
            float val=((Number)((JSONObject) label).get("per_100g")).floatValue()*portionWeight/100;
            switch (((JSONObject) label).get("name").toString()) {
                case "Carbohydrates":
                    nutritionValues[0]=val;
                    break;
                case "Sugars":
                    nutritionValues[1]=val;
                    break;
                case "Proteins":
                    nutritionValues[2]=val;
                    break;
                case "Fat":
                    nutritionValues[3]=val;
                    break;
                case "Saturated Fat":
                    nutritionValues[4]=val;
                    break;
                case "Vitamin A":
                    nutritionValues[5]=val;
                    break;
                case "Vitamin C":
                    nutritionValues[6]=val;
                    break;
                case "Calcium":
                    nutritionValues[7]=val;
                    break;
                case "Sodium":
                    nutritionValues[8]=val;
                    break;
                case "Potassium":
                    nutritionValues[9]=val;
                    break;
                case "Iron":
                    nutritionValues[10]=val;
                    break;
                case "Water":
                    nutritionValues[11]=val;
                    break;
                case "Fiber":
                    nutritionValues[12]=val;
                    break;
            }
        }
        //NUTRIENT
        foodResult.addNutrient(new Carbohydrate(nutritionValues[0],nutritionValues[1]));
        foodResult.addNutrient(new Protein(nutritionValues[2]));
        foodResult.addNutrient(new Lipid(nutritionValues[3],nutritionValues[4]));
        foodResult.addNutrient(new VitaminA(nutritionValues[5]));
        foodResult.addNutrient(new VitaminC(nutritionValues[6]));
        foodResult.addNutrient(new Calcium(nutritionValues[7]));
        foodResult.addNutrient(new Sodium(nutritionValues[8]));
        foodResult.addNutrient(new Potassium(nutritionValues[9]));
        foodResult.addNutrient(new Iron(nutritionValues[10]));

        //NON NUTRIENT
        foodResult.addNotNutrient(new WaterFromFood(nutritionValues[11]));
        foodResult.addNotNutrient(new Fiber(nutritionValues[12]));

        return foodResult;
    }
}