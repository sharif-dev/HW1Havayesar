package com.example.weatherforecast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResponse {
    @SerializedName("features")
    private List<ConvertjsonToCityController> cities;

    public CityResponse() {
    }

    public List<ConvertjsonToCityController> getCities() {
        return cities;
    }

    public void setCities(List<ConvertjsonToCityController> cities) {
        this.cities = cities;
    }
}