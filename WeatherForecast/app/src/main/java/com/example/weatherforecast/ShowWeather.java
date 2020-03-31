package com.example.weatherforecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ShowWeather extends Fragment {
    private ProgressBar progressBar;
    private TextView dailySum;
    private RecyclerView showWeatherFrag;
    private Button daily;
    private Button hourly;
    private Button current;
    private ConvertWeatherInformation convertWeatherInformation;
    private double x , y;
    private boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void startProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void endProgressBar(){
        if (progressBar.isShown())
            progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);

        convertWeatherInformation = new ConvertWeatherInformation();
        convertWeatherInformation.setFunc(this::endProgressBar);

        hourly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertWeatherInformation.startHourlyWeather();
            }
        });

        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertWeatherInformation.startDailyWeather();
            }
        });

        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertWeatherInformation.startCurrentWeather();
            }
        });




        if (flag) {
            convertWeatherInformation.setContext(getActivity());
            convertWeatherInformation.setUrl("https://api.darksky.net/forecast/8b8f535b152b8369106181d94adf2f24/" + x + "," + y);
            convertWeatherInformation.setDailysummary(dailySum);
            convertWeatherInformation.setWeathersFragment(showWeatherFrag);
            convertWeatherInformation.readJson(getActivity());

        }
        else {
            convertWeatherInformation.setContext(getActivity());
            convertWeatherInformation.setDailysummary(dailySum);
            convertWeatherInformation.setWeathersFragment(showWeatherFrag);
            convertWeatherInformation.loadOffline(getActivity());
            progressBar.setVisibility(View.INVISIBLE);
        }
       // convertWeatherInformation.loadOffline(getActivity());
//        endProgressBar();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_weather, container,false);


    }


    public void findViews(View view){
        Log.d("taatat", " i am in find view");
        progressBar = view.findViewById(R.id.loading);
        dailySum = view.findViewById(R.id.daily_summary);
        showWeatherFrag =(RecyclerView) view.findViewById(R.id.weather_fragment);
        hourly = view.findViewById(R.id.hourlyweatherbutton);
        daily = view.findViewById(R.id.dailyweatherbutton);
        current = view.findViewById(R.id.currenweatherbutton);

    }
}
