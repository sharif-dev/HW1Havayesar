package com.example.weatherforecast;

import android.content.Context;
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

public class FindCity extends Fragment {
    private EditText cityFinderEditText;
    private Button findButton;
    private String cityName;
    private String url;
    private RecyclerView citiesFragment;
    List<ConvertjsonToCityController> cities;
    private CityAdapter cityAdapter;
    private ProgressBar progressBar;

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
                progressBar.setVisibility(View.VISIBLE);
                cityName = cityFinderEditText.getText().toString();
                if (cities!=null){
                    cities.clear();
                    cityAdapter.notifyDataSetChanged();
                }
                url = getString(R.string.url , cityName,getString(R.string.token));

                newRequestQueue(url , getActivity());

            }
        });


    }
    private void findViews(View view){
        cityFinderEditText = (EditText) view.findViewById(R.id.input_city);
        findButton = (Button) view.findViewById(R.id.find_city_button);
        citiesFragment =  view.findViewById(R.id.cities_fragment);
        progressBar = view.findViewById(R.id.progress_bar);
    }
    private void newRequestQueue(String url , Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest  = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CityResponse cityResponse = gson.fromJson(response,CityResponse.class);
                cities = cityResponse.getCities();
                initCityList();
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

    private void initCityList(){
        cityAdapter = new CityAdapter(cities);
        citiesFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        citiesFragment.setAdapter(cityAdapter);

    }

}
