package com.example.ryne.apiauthenticationapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("toiday","hehe0");
        DataTask dataTask = new DataTask();
        dataTask.execute();
    }

    private class DataTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String data = HttpHandler.makeServiceCall();
            Log.d("json",data);
            return data;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this,"chay", Toast.LENGTH_LONG).show();
        }
    }

}
