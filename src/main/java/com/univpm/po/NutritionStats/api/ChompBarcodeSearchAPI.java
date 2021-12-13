package com.univpm.po.NutritionStats.api;

import com.univpm.po.NutritionStats.model.Food;
import com.univpm.po.NutritionStats.utility.InputOutputImpl;
import com.univpm.po.NutritionStats.utility.SerializationImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class ChompBarcodeSearchAPI {
    final static String DIR = "api_response/chomp/";
    final static String DROPBOX_DIR="/api_response/chomp/";
    final static String API_KEY = "AzytZXSqpb3nMitJ";
    final static String URL = "https://chompthis.com/api/v2/food/branded/barcode.php?api_key=" + API_KEY;

    public static JSONObject getEanInfo(String ean) {
        //Check if I already have the information needed:
        //in local database
        InputOutputImpl inputOutputEan = new InputOutputImpl(DIR, ean + ".dat");
        if (inputOutputEan.existFile()) {
            SerializationImpl serializationResult = new SerializationImpl(DIR , ean + ".dat");
            return (JSONObject) serializationResult.loadObject();
        }
        //in remote database
        if(DropboxAPI.getFilesInFolder(DROPBOX_DIR).contains(ean + ".dat")){
            DropboxAPI.downloadFile(DROPBOX_DIR+ean + ".dat",DIR + ean + ".dat");
            SerializationImpl serializationResult = new SerializationImpl(DIR , ean + ".dat");
            return (JSONObject) serializationResult.loadObject();
        }

        JSONObject result=null;

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
        SerializationImpl serializationResult = new SerializationImpl(DIR , ean + ".dat");
        serializationResult.saveObject(result);
        DropboxAPI.uploadFile(new File(serializationResult.getFullPath()),DROPBOX_DIR);
        return result;
    }

    public static Food getFood(String eanCode) {
        //TODO conversione json food
        return new Food("",0);
    }
}