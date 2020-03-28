package com.example.weatherforecast;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;

public class ConvertWeatherInformation {
    public String url;
    public double latitude;
    public double longitude;
    public String timeZone;
    public CurrentlyWeather currentlyWeather;
    public ArrayList<CurrentlyWeather> hourlyWeathers;
    public ArrayList<DailyWeather> dailyWeathers;
    private String hourlySummary;
    private String dailySummary;

    public ConvertWeatherInformation(String url) {
        this.url = url;
        hourlyWeathers = new ArrayList<>();
        dailyWeathers = new ArrayList<>();
    }

    public void convert(FragmentActivity f) {
        RequestQueue queue = Volley.newRequestQueue(f);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    timeZone = jsonObject.getString("timezone");
                    latitude = jsonObject.getDouble("latitude");
                    longitude = jsonObject.getDouble("longitude");


                    JSONObject currently = jsonObject.getJSONObject("currently");
                    {
                        currentlyWeather.setHumidity(currently.getDouble("humidity"));
                        currentlyWeather.setIcon(currently.getString("icon"));
                        currentlyWeather.setSummary(currently.getString("summary"));
                        currentlyWeather.setTemperature(currently.getDouble("temperature"));
                        currentlyWeather.setTime(new Time(currently.getLong("time")));
                        currentlyWeather.setWindSpeed(currently.getDouble("windSpeed"));
                    }


                    JSONObject temp = jsonObject.getJSONObject("hourly");
                    hourlySummary = temp.getString("summary");
                    JSONArray hours = temp.getJSONArray("data");
                    {
                        for (int i=0 ; i < hours.length() ; i++){
                            JSONObject tempObj = hours.getJSONObject(i);
                            CurrentlyWeather tempHour = new CurrentlyWeather();
                            tempHour.setHumidity(tempObj.getDouble("humidity"));
                            tempHour.setIcon(tempObj.getString("icon"));
                            tempHour.setSummary(tempObj.getString("summary"));
                            tempHour.setTemperature(tempObj.getDouble("temperature"));
                            tempHour.setTime(new Time(tempObj.getLong("time")));
                            tempHour.setWindSpeed(tempObj.getDouble("windSpeed"));

                            hourlyWeathers.add(tempHour);
                        }
                    }


                    temp = jsonObject.getJSONObject("daily");
                    dailySummary = temp.getString("summary");
                    JSONArray days = temp.getJSONArray("data");
                    {
                        for (int i=0 ; i < days.length() ; i++){
                            DailyWeather tempDay = new DailyWeather();
                            JSONObject tempObj = days.getJSONObject(i);
                            tempDay.setHighestTemperature(tempObj.getDouble("temperatureHigh"));
                            tempDay.setLowestTemperature(tempObj.getDouble("temperatureLow"));
                            tempDay.setHumidity(tempObj.getDouble("humidity"));
                            tempDay.setIcon(tempObj.getString("icon"));
                            tempDay.setSunriseTime(new Time(tempObj.getLong("sunriseTime")));
                            tempDay.setSunsetTime(new Time(tempObj.getLong("sunsetTime")));
                            tempDay.setTime(new Time(tempObj.getLong("time")));
                            tempDay.setSummary(tempObj.getString("summary"));
                            tempDay.setWindSpeed(tempObj.getDouble("windSpeed"));


                            dailyWeathers.add(tempDay);
                        }
                    }
                    Log.d("havaye sar",  dailySummary);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Taagg", "naaaaaaaaaaaaaaaaaaaaaaa");
            }

        });

        queue.add(stringRequest);


    }

}


