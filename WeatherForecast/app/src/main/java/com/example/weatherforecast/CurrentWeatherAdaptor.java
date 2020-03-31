package com.example.weatherforecast;

import android.annotation.SuppressLint;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CurrentWeatherAdaptor extends RecyclerView.Adapter<CurrentWeatherAdaptor.ViewHolder> {
    private List<CurrentlyWeather> weathers;
    private String timeZone;

    public CurrentWeatherAdaptor(List<CurrentlyWeather> weathers, String timeZone) {
        this.weathers = weathers;
        this.timeZone = timeZone;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_current_weather , parent , false );
        return new ViewHolder(view);
    }
    private void setImage(String url , ViewHolder holder){
        Log.d("Ttttt" , url);



        if (url.equals("clear-day")) {
            holder.constraintLayout.setBackgroundResource(R.drawable.clearday);

        }
        if (url.equals("clear-night")){
            holder.constraintLayout.setBackgroundResource(R.drawable.clearnight);

        }
        if (url.equals("rain")) {
            holder.constraintLayout.setBackgroundResource(R.drawable.rain);

        }

        if (url.equals("snow")) {
            holder.constraintLayout.setBackgroundResource(R.drawable.snow);

        }
        if (url.equals("wind")) {
            holder.constraintLayout.setBackgroundResource(R.drawable.wind);


        }
        if (url.equals("fog")) {
            holder.constraintLayout.setBackgroundResource(R.drawable.fog);

        }
        if (url.equals("cloudy")) {
            Log.d("taat", "haramama");
            holder.constraintLayout.setBackgroundResource(R.drawable.cloudy);

        }
        if (url.equals("partly-cloudy-day")) {
            holder.constraintLayout.setBackgroundResource(R.drawable.partlycloudyday);

        }
        if (url.equals("partly-cloudy-night")) {
            holder.constraintLayout.setBackgroundResource(R.drawable.partycloudynight);

        }

    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Date date = new Date(weathers.get(position).getTime() * 1000);
        DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd \n EEEE \n HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone(this.timeZone));
        String dateFormatted = formatter.format(date);
        holder.summary.setText(dateFormatted + "\n\n" + weathers.get(position).getSummary() + "\n\n wind speed: " + weathers.get(position).getWindSpeed());
        holder.highestTemp.setText("temperature is\n " + String.valueOf((int)weathers.get(position).getTemperature()) + " ℃\n\n" + "humidity: " + (int)(weathers.get(position).getHumidity() * 100) + "%\n\n UVIndex: " + weathers.get(position).getUvIndex());
        holder.icon.setText(weathers.get(position).getIcon() + "\n\n\n\n" + weathers.get(position).getSummary());
        String url =  weathers.get(position).getIcon();
        setImage(url , holder);


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
