package com.example.ryne.apiauthenticationapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class Register extends AppCompatActivity {
    EditText edName, edEmail, edPassword;
    String name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edName = (EditText) findViewById(R.id.name);
        edEmail = (EditText) findViewById(R.id.email);
        edPassword = (EditText) findViewById(R.id.password);
    }

    public void sendDataToServer(View view) {
        name = edName.getText().toString();
        email = edEmail.getText().toString();
        password = edPassword.getText().toString();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("password", password);
            jsonObject.put("email", email);
            jsonObject.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonObject.length() > 0) {
            new RegisterTask().execute(String.valueOf(jsonObject));
            //Log.d("daynuane", String.valueOf(jsonObject));
            //call to sync class
        }
    }

    class RegisterTask extends AsyncTask<String, String, String>{
          @Override
            protected String doInBackground(String... params) {
              String JsonResponse = null;
              String JsonData = params[0];

              HttpURLConnection urlConnection = null;
              BufferedReader reader = null;
              try {
                  URL url = new URL("http://10.0.3.2:3000/users");
                  urlConnection = (HttpURLConnection) url.openConnection();
                  urlConnection.setDoOutput(true);
                  //
                  urlConnection.setRequestMethod("POST");
                  urlConnection.setRequestProperty("Content-Type", "application/json");
                  urlConnection.setRequestProperty("Accept","application/json");
                  // set headers and method
                  Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(),"UTF-8"));
                  writer.write(JsonData);
                  //json data
                  writer.close();
                  //Log.d("dayroi", "heeje");
              } catch (IOException e) {
                  e.printStackTrace();
              } finally {
                  if (urlConnection != null){
                      urlConnection.disconnect();
                  }
                  if (reader != null){
                      try {
                          reader.close();
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }
              }
              return null;
            }
    }

}

