package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Log.d("taag" ,"hello"+ ConnectivityHelper.isConnectedToNetwork(this));
        if (ConnectivityHelper.isConnectedToNetwork(this)){

        FindCity findCity = new FindCity();
        Log.d("eee" , "jooo");
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,findCity).addToBackStack(null).commit(); }
        else {
            Toast.makeText(this , "No internet available" , Toast.LENGTH_LONG).show();

            ShowWeather showWeather = new ShowWeather();
            showWeather.setFlag(false);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container , showWeather).addToBackStack(null).commit();

        }

    }
}
