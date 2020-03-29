package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.d("aa","aa");
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,findCity)
        FindCity findCity = new FindCity();
        Log.d("eee" , "jooo");
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,findCity).addToBackStack(null).commit();    }
}
