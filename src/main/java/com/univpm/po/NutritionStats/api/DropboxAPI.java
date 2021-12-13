package com.univpm.po.NutritionStats.api;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DropboxAPI {
    final static String BEARER_TOKEN = "QCEK9HXIdv4AAAAAAAAAAYeLlhwrP9XlPIWwbnGe-I2RnFK4UV2luAU6uHNIf5Ck";
    final static String ENDPOINT_UPLOAD = "https://content.dropboxapi.com/2/files/upload";
    final static String ENDPOINT_DOWNLOAD = "https://content.dropboxapi.com/2/files/download";
    final static String ENDPOINT_LIST_FOLDER = "    https://api.dropboxapi.com/2/files/list_folder";
    private static final int BUFFER_SIZE = 1024;

    public static JSONObject uploadFile(File fileLocal, String pathCloud) {
        /*
         * see documentation here for details:
         * https://www.dropbox.com/developers/documentation/http/documentation#files-upload
         */
        JSONObject result = null;
        pathCloud = pathCloud.replace('\\', '/');
        HttpURLConnection conn = null;
        try {
            //send request
            conn = (HttpURLConnection) new URL(ENDPOINT_UPLOAD).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + BEARER_TOKEN);
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("Dropbox-API-Arg", "{\"path\": \"" + pathCloud + fileLocal.getName() +
                    "\",\"mode\": \"overwrite\",\"autorename\": false,\"mute\": false,\"strict_conflict\": false}");
            conn.setDoOutput(true);
            IOUtils.copy(new FileInputStream(fileLocal), conn.getOutputStream());

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
            try {
                result = (JSONObject) new JSONParser().parse("{\"result\":\"IOException\"}");
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static boolean downloadFile(String pathCloud, String pathLocal) {
        /*
         * see documentation here for details:
         * https://www.dropbox.com/developers/documentation/http/documentation#files-download
         */
        pathCloud = pathCloud.replace('\\', '/');
        HttpURLConnection conn = null;
        try {
            //send request
            conn = (HttpURLConnection) new URL(ENDPOINT_DOWNLOAD).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + BEARER_TOKEN);
            conn.setRequestProperty("Dropbox-API-Arg", "{\"path\": \"" + pathCloud + "\"}");
            conn.setDoOutput(true);

            // opens an output stream to save file
            InputStream inputStream = conn.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(pathLocal);
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static ArrayList<String> getFilesInFolder(String folderPath){
        /*
         * see documentation here for details:
         * https://www.dropbox.com/developers/documentation/http/documentation#files-list_folder
         */
        ArrayList<String> result=new ArrayList<>();
        folderPath = folderPath.replace('\\', '/');
        HttpURLConnection conn = null;
        try {
            //send request
            conn = (HttpURLConnection) new URL(ENDPOINT_LIST_FOLDER).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + BEARER_TOKEN);
            conn.setDoOutput(true);

            //send json request
            String jsonBody="{\r\n\"path\":\""+folderPath+"\"\r\n}";
            try(OutputStream os= conn.getOutputStream()){
                byte[] input=jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }

            //read json response
            StringBuilder data = new StringBuilder();
            String line = "";
            try (InputStream in = conn.getInputStream();) {
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));
                while ((line = buf.readLine()) != null)
                    data.append(line);
            }
            JSONObject response = (JSONObject) JSONValue.parseWithException(data.toString());

            //get list of files from response
            JSONArray fileList = (JSONArray) response.get("entries");
            for(Object jo : fileList)
                result.add(((JSONObject)jo).get("name").toString());

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
