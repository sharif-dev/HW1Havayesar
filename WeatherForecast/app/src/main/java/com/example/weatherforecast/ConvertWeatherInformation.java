package com.example.weatherforecast;

import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ConvertWeatherInformation {
    public String url;
    public double latitude;
    public double longitude;
    public String timeZone;

    public ConvertWeatherInformation(String url) {
        this.url = url;
    }

    public void convert() {
        StringRequest response = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Taagg", "kir to kazemi");
            }

        });




    }

}


