package com.example.weatherforecast;

import java.sql.Time;

public class DailyWeather {
    private long time ;
    private String summary;
    private String icon;
    private double humidity;
    private double lowestTemperature;
    private double highestTemperature;
    private long sunriseTime, sunsetTime;
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

    public DailyWeather(){

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

    public double getLowestTemperature() {
        return lowestTemperature;
    }

    public void setLowestTemperature(double lowestTemperature) {
        this.lowestTemperature = (lowestTemperature - 32) * 5 / 9;
    }

    public double getHighestTemperature() {
        return highestTemperature;
    }

    public void setHighestTemperature(double highestTemperature) {
        this.highestTemperature =( highestTemperature - 32) * 5 / 9;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getSunriseTime() {
        return sunriseTime;
    }

    public long getTime() {
        return time;
    }

    public void setSunriseTime(long sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public long getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(long sunsetTime) {
        this.sunsetTime = sunsetTime;
    }
}
