package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FindCity findCity = new FindCity();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,findCity)
                .addToBackStack(null).commit();
    }
}
