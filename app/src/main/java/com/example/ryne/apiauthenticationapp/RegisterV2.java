package com.example.ryne.apiauthenticationapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class RegisterV2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_v2);
        new SendPostRequest().execute();
    }
    public class SendPostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://10.0.3.2:3000/users");
                JSONObject jsonObject = new JSONObject();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject.put("name","sida");
                jsonObject.put("email","sida@gmail.com");
                jsonObject.put("password","hochiminh");
               // jsonObject1.put("user",jsonObject);
                Log.d("sida",jsonObject.toString());

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //
                //
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                OutputStream outputStream= connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(outputStream, "UTF-8"));
               // Log.d("chuyendoi", getPostDataString(jsonObject));
               // writer.write(getPostDataString(jsonObject));
                Log.d("chuoijson",String.valueOf(jsonObject));
                writer.write(String.valueOf(jsonObject));
                writer.flush();
                writer.close();
                outputStream.close();

                int respondeCode = connection.getResponseCode();
                if(respondeCode == HttpURLConnection.HTTP_OK){
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    StringBuffer stringBuffer = new StringBuffer("");
                    String line = "";
                    while((line = bufferedReader.readLine())!= null){
                        stringBuffer.append(line);
                        break;
                    }
                    bufferedReader.close();
                    return stringBuffer.toString();
                }else{
                    return new String("false:" +respondeCode);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        public String getPostDataString(JSONObject params) throws Exception {
            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while(itr.hasNext()){

                String key= itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }
    }
}
