package com.example.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.List;

public class FindCity extends Fragment implements CityAdapter.OnNoteListener {
    private EditText cityFinderEditText;
    private Button findButton;
    private String cityName;
    private String url;
    private RecyclerView citiesFragment;
    List<ConvertjsonToCityController> cities;
    private CityAdapter cityAdapter;
    private ProgressBar progressBar;
    private double x , y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_findcity , container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                cityName = cityFinderEditText.getText().toString();
                if (cities!=null){

                    cities.clear();
                    cityAdapter.notifyDataSetChanged();
                    Log.d("Taaag","kkei");
                }

                progressBar.setVisibility(View.VISIBLE);
                url = getString(R.string.url , cityName,getString(R.string.token));
                newRequestQueue(url);


            }
        });


    }

    private void findViews(View view){
        cityFinderEditText = (EditText) view.findViewById(R.id.input_city);
        findButton = (Button) view.findViewById(R.id.find_city_button);
        citiesFragment =  view.findViewById(R.id.cities_fragment);
        progressBar = view.findViewById(R.id.progress_bar);
    }
    private void newRequestQueue(String url) {
        StringRequest stringRequest;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        System.out.println(url);
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                CityResponse cityResponse = gson.fromJson(response, CityResponse.class);
//                if (cityResponse.getCities()!=null) {



                cities = cityResponse.getCities();
                Log.d("Tag", "i am in reques" + cities);
                initCityList(getActivity());
                if (progressBar.isShown()) {
                    progressBar.setVisibility(View.INVISIBLE);
                }

                cityAdapter.notifyDataSetChanged();
//                }
//                else {
//                    Toast.makeText(context,"invalid city ", Toast.LENGTH_LONG);
//                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Taagg", "kir to kazemi");
            }

        });

        queue.add(stringRequest);
        Log.d("tag", "after queue" + cities);

    }
    private void initCityList(Context context){
        cityAdapter = new CityAdapter(cities,this);
        citiesFragment.setLayoutManager(new LinearLayoutManager(context));
        citiesFragment.setAdapter(cityAdapter);

    }


    @Override
    public void onNoteClick(int position) {
        x = cities.get(position).getArr()[0];
        y = cities.get(position).getArr()[1];
        Log.d("TAg" , "x is" + x);
        Log.d("Tag" , "y is" + y);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ShowWeather showWeather = new ShowWeather();
        showWeather.setX(x);
        showWeather.setY(y);
        ft.replace(R.id.fragment_container , showWeather);
        ft.addToBackStack(null);
        ft.commit();


    }
}
