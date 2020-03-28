package com.example.weatherforecast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ShowWeather extends Fragment {
    private ProgressBar progressBar;
    private Button button;
    private ConvertWeatherInformation convertWeatherInformation;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        convertWeatherInformation = new ConvertWeatherInformation("https://api.darksky.net/forecast/8b8f535b152b8369106181d94adf2f24/5.656,6.343?exclude:currectly,hourly,daily");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertWeatherInformation.convert(getActivity());
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_weather, container,false);

    }


    public void findViews(View view){
        button = (Button) view.findViewById(R.id.find_city_button);
        progressBar = view.findViewById(R.id.progress_bar);
    }
}
