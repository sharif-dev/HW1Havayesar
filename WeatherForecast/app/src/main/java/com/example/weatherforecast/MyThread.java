package com.example.weatherforecast;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
public class MyThread implements Runnable {
    Context context;
    Activity activity;
    FragmentTransaction fragmentTransaction;

    public FragmentTransaction getFragmentTransaction() {
        return fragmentTransaction;
    }

    public void setFragmentTransaction(FragmentTransaction fragmentTransaction) {
        this.fragmentTransaction = fragmentTransaction;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        Log.d("taag" ,"hello"+ ConnectivityHelper.isConnectedToNetwork(context));
        if (ConnectivityHelper.isConnectedToNetwork(context)){

            FindCity findCity = new FindCity();
            Log.d("eee" , "jooo");
            fragmentTransaction.add(R.id.fragment_container,findCity).addToBackStack(null).commit(); }
        else {
            Toast.makeText(context , "No internet available" , Toast.LENGTH_LONG).show();

            ShowWeather showWeather = new ShowWeather();
            showWeather.setFlag(false);
            fragmentTransaction.add(R.id.fragment_container , showWeather).addToBackStack(null).commit();

        }

    }
}
