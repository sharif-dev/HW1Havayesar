package com.example.weatherforecast;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

public class VolleyHandler extends AsyncTask<Void ,Void , Void> {
    private String url;
    private RecyclerView citiesFragment;
    List<ConvertjsonToCityController> cities;
    private CityAdapter cityAdapter;
    private ProgressBar progressBar;
    private Context context;
    public VolleyHandler(String url, RecyclerView citiesFragment, List<ConvertjsonToCityController> cities, ProgressBar progressBar , Context context) {
        this.url = url;
        this.citiesFragment = citiesFragment;
        this.cities = cities;
        this.progressBar = progressBar;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (cities!=null){
            cities.clear();
            cityAdapter.notifyDataSetChanged();
        }
        newRequestQueue(url , context);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressBar.setVisibility(View.INVISIBLE);
    }
    private void newRequestQueue(String url , Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest  = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CityResponse cityResponse = gson.fromJson(response,CityResponse.class);
                cities = cityResponse.getCities();
                initCityList(context);
                if (progressBar.isShown()){
                    progressBar.setVisibility(View.INVISIBLE);
                }

                cityAdapter.notifyDataSetChanged();
                Log.d("taag","chooni"+ cities.get(0).getPlaceName());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Taagg" , "kir to kazemi");
            }

        });
        queue.add(stringRequest);


    }

    public List<ConvertjsonToCityController> getCities() {
        return cities;
    }

    public void setCities(List<ConvertjsonToCityController> cities) {
        this.cities = cities;
    }

    private void initCityList(Context context){
        cityAdapter = new CityAdapter(cities);
        citiesFragment.setLayoutManager(new LinearLayoutManager(context));
        citiesFragment.setAdapter(cityAdapter);

    }
}
