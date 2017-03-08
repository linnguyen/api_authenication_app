package com.example.ryne.apiauthenticationapp;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lin on 26/12/2016.
 */

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler(){
    }
    public static String makeServiceCall(){
        String response = null;
        try {
          //  URL url = new URL("http://10.0.2.2:3000/users.json");
            URL url = new URL("http://10.0.3.2:3000/users.json");
           // URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?lat=35&lon=139&cnt=10&mode=json&appid=be8d3e323de722ff78208a7dbb2dcd6f");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // read the response
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            response = covertStreamToString(inputStream);
            Log.d("toiday","hehe");
        }catch (Exception ex){
            Log.d("loine",ex.toString());
            ex.getStackTrace();
        }
        return response;
    }

    private static String covertStreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try{
         while((line = reader.readLine())!= null){
            stringBuilder.append(line).append('\n');
        }
        }catch (Exception ex){
            ex.getStackTrace();
        }
        return stringBuilder.toString();
    }
}
