package com.example.weatherforecast;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShowWheaterAdapter extends RecyclerView.Adapter<ShowWheaterAdapter.ViewHolder> {
    private List<DailyWeather> weathers;

    public ShowWheaterAdapter(List<DailyWeather> weathers) {
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_daily_weather , parent , false );
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("ttaqq" , weathers.get(2).getSummary());
        holder.summary.setText(weathers.get(position).getSummary());
        holder.highestTemp.setText("highest temperture is" + String.valueOf(weathers.get(position).getHighestTemperature()) + "\nLowenst temperture is "+String.valueOf(weathers.get(position).getLowestTemperature()));
        holder.icon.setText(weathers.get(position).getIcon());
        String url =  weathers.get(position).getIcon().replaceAll("-" ,"" );
//        int u = Integer.parseInt(weathers.get(position).getIcon().replaceAll("-" ,"" ))
        Log.d("ttt" , url);
//        holder.constraintLayout.setBackground(Drawable.createFromPath( url+".jpeg"));
        Uri otherPath = Uri.parse("android.resource://com.e.test/drawable/icon");
        holder.constraintLayout.setBackground(Drawable.createFromPath("/home/alireza/Desktop/HW1Havayesar/WeatherForecast/app/src/main/res/drawable-v24/"+url+".jpeg"));


    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView summary;
        public TextView highestTemp;
        public TextView icon;
        public LinearLayoutCompat constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            summary = itemView.findViewById(R.id.day_weather);
            highestTemp = itemView.findViewById(R.id.lowes_highest_temperture);
            icon=itemView.findViewById(R.id.icon);
            constraintLayout=itemView.findViewById(R.id.container_of_recycle);
        }
    }
}
