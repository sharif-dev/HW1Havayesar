package com.example.weatherforecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;

public class ConvertWeatherInformation {
    private String url;
    private double latitude;
    private double longitude;
    private String timeZone;
    private RecyclerView weathersFragment;
    private ShowDailyWeatherAdaptor showDailyWeatherAdaptor;
    private HourlyWeatherAdaptor hourlyWeatherAdaptor;
    private CurrentWeatherAdaptor currentWeatherAdaptor;
    private ArrayList<CurrentlyWeather> currentlyWeathers;
    private ArrayList<CurrentlyWeather> hourlyWeathers;
    private ArrayList<DailyWeather> dailyWeathers;
    private String hourlySummary;
    private String dailySummary;
   private TextView dailysummary;
    private Context context;
    private Runnable func;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWeathersFragment(RecyclerView weathersFragment) {
        this.weathersFragment = weathersFragment;
    }

    public void setDailysummary(TextView dailysummary) {
        this.dailysummary = dailysummary;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ConvertWeatherInformation() {
        hourlyWeathers = new ArrayList<>();
        dailyWeathers = new ArrayList<>();
        currentlyWeathers = new ArrayList<>();

    }

    public void setFunc(Runnable func){
        this.func = func;
    }


    public void readJson(FragmentActivity fragmentActivity) {
        RequestQueue queue = Volley.newRequestQueue(fragmentActivity);
        if (weathersFragment == null){
            Log.d("Tag" , "i am null pointer");
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        System.out.println(response);
                        saveSharedPreferences(fragmentActivity, response);
                        convert(response);
//                        loadOffline(fragmentActivity);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getCause().getCause().getMessage());
            }
        });

        queue.add(stringRequest);
    }

    public void saveSharedPreferences(FragmentActivity fragmentActivity, String jsonText) {
        SharedPreferences sharedPreferences = fragmentActivity.getSharedPreferences("savedJSON", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString("jsonText", jsonText);
        editor.commit();
    }


    public void loadOffline(FragmentActivity fragmentActivity) {
        SharedPreferences sharedPreferences = fragmentActivity.getSharedPreferences("savedJSON", Context.MODE_PRIVATE);
        String jsonText = sharedPreferences.getString("jsonText", "");
        convert(jsonText);
    }


    public void convert(String jsonFile) {

        System.out.println(jsonFile);
        try {
            JSONObject jsonObject = new JSONObject(jsonFile);
            timeZone = jsonObject.getString("timezone");
            latitude = jsonObject.getDouble("latitude");
            longitude = jsonObject.getDouble("longitude");

            CurrentlyWeather currentlyWeather = new CurrentlyWeather();
            JSONObject currently = jsonObject.getJSONObject("currently");
            {
                currentlyWeather.setHumidity(currently.getDouble("humidity"));
                currentlyWeather.setIcon(currently.getString("icon"));
                currentlyWeather.setSummary(currently.getString("summary"));
                currentlyWeather.setTemperature(currently.getDouble("temperature"));
                currentlyWeather.setTime(currently.getLong("time"));
                currentlyWeather.setWindSpeed(currently.getDouble("windSpeed"));
                currentlyWeather.setUvIndex(currently.getInt("uvIndex"));
                currentlyWeather.setPreCipProbability(currently.getDouble("precipProbability"));
                if (currentlyWeather.getPreCipProbability() > 0 && currently.getDouble("precipIntensity") > 0) {
                    currentlyWeather.setPreCipType(currently.getString("precipType"));
                }

                this.currentlyWeathers.add(currentlyWeather);
            }


            JSONObject temp = jsonObject.getJSONObject("hourly");
            hourlySummary = temp.getString("summary");
            JSONArray hours = temp.getJSONArray("data");
            {
                for (int i = 0; i < hours.length(); i++) {
                    JSONObject tempObj = hours.getJSONObject(i);
                    CurrentlyWeather tempHour = new CurrentlyWeather();
                    tempHour.setHumidity(tempObj.getDouble("humidity"));
                    tempHour.setIcon(tempObj.getString("icon"));
                    tempHour.setSummary(tempObj.getString("summary"));
                    tempHour.setTemperature(tempObj.getDouble("temperature"));
                    tempHour.setTime(tempObj.getLong("time"));
                    tempHour.setWindSpeed(tempObj.getDouble("windSpeed"));
                    tempHour.setUvIndex(tempObj.getInt("uvIndex"));
                    tempHour.setPreCipProbability(tempObj.getDouble("precipProbability"));
                    if (tempHour.getPreCipProbability() > 0 && tempObj.getDouble("precipIntensity") > 0) {
                        tempHour.setPreCipType(tempObj.getString("precipType"));
                    }
                    hourlyWeathers.add(tempHour);
                }
            }


            temp = jsonObject.getJSONObject("daily");
            dailySummary = temp.getString("summary");
            Log.d("saalaam", dailySummary);

            JSONArray days = temp.getJSONArray("data");
            {
                for (int i = 0; i < days.length(); i++) {
                    DailyWeather tempDay = new DailyWeather();
                    JSONObject tempObj = days.getJSONObject(i);
                    tempDay.setHighestTemperature(tempObj.getDouble("temperatureHigh"));
                    tempDay.setLowestTemperature(tempObj.getDouble("temperatureLow"));
                    tempDay.setHumidity(tempObj.getDouble("humidity"));
                    tempDay.setIcon(tempObj.getString("icon"));
                    tempDay.setSunriseTime(tempObj.getLong("sunriseTime"));
                    tempDay.setSunsetTime(tempObj.getLong("sunsetTime"));
                    tempDay.setTime(tempObj.getLong("time"));
                    tempDay.setSummary(tempObj.getString("summary"));
                    tempDay.setWindSpeed(tempObj.getDouble("windSpeed"));
                    tempDay.setUvIndex(tempObj.getInt("uvIndex"));
                    tempDay.setPreCipProbability(tempObj.getDouble("precipProbability"));
                    if (tempDay.getPreCipProbability() > 0 && tempObj.getDouble("precipIntensity") > 0) {
                        tempDay.setPreCipType(tempObj.getString("precipType"));
                    }

                    dailyWeathers.add(tempDay);
                }
                initWeatherFragment(context);
                currentWeatherAdaptor.notifyDataSetChanged();
                func.run();
            }
            Log.d("havaye sar", dailyWeathers.get(3).getSummary());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initWeatherFragment(Context context){
        Log.d("tag" , "daala");
        currentWeatherAdaptor = new CurrentWeatherAdaptor(currentlyWeathers, timeZone);
        hourlyWeatherAdaptor = new HourlyWeatherAdaptor(hourlyWeathers, timeZone);
        showDailyWeatherAdaptor = new ShowDailyWeatherAdaptor(dailyWeathers, timeZone);
        weathersFragment.setLayoutManager(new LinearLayoutManager(context));
        weathersFragment.setAdapter(currentWeatherAdaptor);

    }

    public void startDailyWeather(){
        weathersFragment.setAdapter(showDailyWeatherAdaptor);
        showDailyWeatherAdaptor.notifyDataSetChanged();
    }

    public void startHourlyWeather(){
        weathersFragment.setAdapter(hourlyWeatherAdaptor);
        hourlyWeatherAdaptor.notifyDataSetChanged();
    }

    public void startCurrentWeather(){
        weathersFragment.setAdapter(currentWeatherAdaptor);
        currentWeatherAdaptor.notifyDataSetChanged();
    }


}


