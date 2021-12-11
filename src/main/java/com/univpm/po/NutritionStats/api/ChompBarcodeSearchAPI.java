package com.univpm.po.NutritionStats.api;

import com.univpm.po.NutritionStats.utility.InputOutputImpl;
import com.univpm.po.NutritionStats.utility.SerializationImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ChompBarcodeSearchAPI {
    final static String DIR = "api_response/chomp/";
    final static String API_KEY = "AzytZXSqpb3nMitJ";
    final static String URL = "https://chompthis.com/api/v2/food/branded/barcode.php?api_key=" + API_KEY;

    public static JSONObject getEanInfo(String ean) {
        //Check in my database if I already have the information needed
        InputOutputImpl inputOutputEan = new InputOutputImpl(DIR, ean + ".dat");
        if (inputOutputEan.existFile()) {
            SerializationImpl serializationResult = new SerializationImpl(DIR + ean + ".dat");
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
        SerializationImpl serializationResult = new SerializationImpl(DIR + ean + ".dat");
        serializationResult.saveObject(result);
        return result;
    }
}