package com.example.weatherforecast;

import com.android.volley.toolbox.StringRequest;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ConvertjsonToCityController {
    public ConvertjsonToCityController() {
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double[] getArr() {
        return arr;
    }

    public void setArr(double[] arr) {
        this.arr = arr;
    }

    @SerializedName("place_name")
    private String placeName;
    @SerializedName("center")
    private double[] arr;
}
