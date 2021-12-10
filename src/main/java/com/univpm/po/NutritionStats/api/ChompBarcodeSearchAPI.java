package com.univpm.po.NutritionStats.api;

import com.fasterxml.jackson.core.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class ChompBarcodeSearchAPI {
    final static String apiKey="AzytZXSqpb3nMitJ";
    final static String url="https://chompthis.com/api/v2/food/branded/barcode.php?api_key="+apiKey;

    public static JSONObject getEanInfo(String ean){
        String url = ChompBarcodeSearchAPI.url+"&code="+ean;
        RestTemplate rt = new RestTemplate();
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(rt.getForObject(url,String.class));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
