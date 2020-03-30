package com.example.weatherforecast;

import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.sql.Time;


public class CurrentlyWeather {
    private Time time ;
    private String summary;
    private String icon;
    private double humidity;
    private double temperature;
    private double windSpeed;
    private double preCipProbability;
    private String preCipType;
    private int uvIndex;


    public int getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public double getPreCipProbability() {
        return preCipProbability;
    }

    public void setPreCipProbability(double preCipProbability) {
        this.preCipProbability = preCipProbability;
    }

    public String getPreCipType() {
        return preCipType;
    }

    public void setPreCipType(String preCipType) {
        this.preCipType = preCipType;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public CurrentlyWeather(){

    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = (temperature - 32) * 5 / 9;
    }
}
