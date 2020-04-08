package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    HandlerThread handlerThread = new HandlerThread("handlerThread");
    Handler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handlerThread.start();
        myHandler = new Handler(handlerThread.getLooper());

        MyThread myThread = new MyThread();
        myThread.setContext(this);
        myThread.setFragmentTransaction(getSupportFragmentManager().beginTransaction());
        myHandler.post(myThread);
        myHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(i);
                    SystemClock.sleep(1000);
                }
            }
        });


    }
}
