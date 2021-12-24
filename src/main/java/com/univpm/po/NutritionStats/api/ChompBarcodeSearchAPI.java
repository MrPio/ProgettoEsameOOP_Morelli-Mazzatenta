package com.univpm.po.NutritionStats.api;

import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.exception.ApiFoodNotFoundException;
import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.exception.ChompLimitOvercameException;
import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.model.nutrient.*;
import com.univpm.po.NutritionStats.utility.InputOutputImpl;
import com.univpm.po.NutritionStats.utility.SerializationImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ChompBarcodeSearchAPI {
    final static String DIR = "api_response/chomp/";
    final static String DROPBOX_DIR = "/api_response/chomp/";
    final static String API_KEY = "AzytZXSqpb3nMitJ";
    final static String URL = "https://chompthis.com/api/v2/food/branded/barcode.php?api_key=" + API_KEY;

    public static JSONObject getEanInfo(long eanConde) throws ApiFoodNotFoundException, ChompLimitOvercameException {
        //Check if I already have the information needed:
        //in local database
        InputOutputImpl inputOutputEan = new InputOutputImpl(DIR, eanConde + ".dat");
        if (inputOutputEan.existFile()) {
            SerializationImpl serializationResult = new SerializationImpl(DIR, eanConde + ".dat");
            return (JSONObject) serializationResult.loadObject();
        }
        //in remote database
        if (DropboxAPI.getFilesInFolder(DROPBOX_DIR).contains(eanConde + ".dat")) {
            DropboxAPI.downloadFile(DROPBOX_DIR + eanConde + ".dat", DIR + eanConde + ".dat");
            SerializationImpl serializationResult = new SerializationImpl(DIR, eanConde + ".dat");
            serializationResult.loadObject();
            return (JSONObject) serializationResult.loadObject();
        }

        //check if I overcame the limit
        DropboxAPI.downloadFile(DROPBOX_DIR+"limit.txt",DIR+"limit.txt");
        InputOutputImpl io=new InputOutputImpl(DIR,"limit.txt");
        String content=io.readFile();
        int count=Integer.parseInt(content.split(":")[0]);
        int limit=Integer.parseInt(content.split(":")[1]);
        if(count>=limit)throw new ChompLimitOvercameException(limit);
        io.writeFile(++count +":"+ limit);
        DropboxAPI.uploadFile(new File(io.getFullPath()),DROPBOX_DIR);

        JSONObject result = null;
        HttpStatus httpStatus = null;

        String url = ChompBarcodeSearchAPI.URL + "&code=" + eanConde;
        RestTemplate rt = new RestTemplate();
        JSONParser parser = new JSONParser();
        try {
            result = (JSONObject) parser.parse(rt.getForObject(url, String.class));
            httpStatus = HttpStatus.OK;
        } catch (HttpClientErrorException e) {
            throw new ApiFoodNotFoundException(eanConde);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //store the result to avoid different future calls on this same request
        SerializationImpl serializationResult = new SerializationImpl(DIR, eanConde + ".dat");
        serializationResult.saveObject(result);
        DropboxAPI.uploadFile(new File(serializationResult.getFullPath()), DROPBOX_DIR);
        return result;
    }

    public static Food getFood(long eanCode, int portionWeight) throws ApiFoodNotFoundException, ChompLimitOvercameException {
        Food foodResult;

        JSONObject foodInfo = (JSONObject) ((JSONArray) getEanInfo(eanCode).get("items")).get(0);

        //creazione food
        JSONObject dietLabels = (JSONObject) (foodInfo.get("diet_labels"));
        Diet diet = null;

        if (((JSONObject) dietLabels.get("vegan")).get("is_compatible") == null)
            diet = Diet.VEGAN;
        else if ((boolean) ((JSONObject) dietLabels.get("vegan")).get("is_compatible"))
            diet = Diet.VEGAN;

        else if (((JSONObject) dietLabels.get("vegetarian")).get("is_compatible") == null)
            diet = Diet.VEGETARIAN;
        else if ((boolean) ((JSONObject) dietLabels.get("vegetarian")).get("is_compatible"))
            diet = Diet.VEGETARIAN;

        /*else if (pescetarian) TODO pescetarian
            diet = Diet.PESCATARIAN;*/
        else
            diet = Diet.CLASSIC;

        int newWeight = portionWeight;
        foodResult = new Food(foodInfo.get("name").toString(), newWeight, Measure.GR, diet);

        JSONArray foodNutrientsInfo = (JSONArray) foodInfo.get("nutrients");
        float[] nutritionValues = new float[20];
        Map<AllNutrientNonNutrient, Float> nutritions = new HashMap<AllNutrientNonNutrient, Float>() {
            {
                for (AllNutrientNonNutrient nutrient : AllNutrientNonNutrient.values())
                    put(nutrient, 0.0f);
            }
        };

        for (Object label : foodNutrientsInfo) {
            float value = ((Number) ((JSONObject) label).get("per_100g")).floatValue() * portionWeight / 100;
            for (Map.Entry<AllNutrientNonNutrient, Float> entry : nutritions.entrySet())
                if (((JSONObject) label).get("name").toString().equals(entry.getKey().getChompKeyWord()))
                    nutritionValues[entry.getKey().ordinal()] = value;
        }
        //NUTRIENT
        foodResult.addNutrient(new Carbohydrate(nutritionValues[0], nutritionValues[1]));
        foodResult.addNutrient(new Protein(nutritionValues[2]));
        foodResult.addNutrient(new Lipid(nutritionValues[3], nutritionValues[4]));
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