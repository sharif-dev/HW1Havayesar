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
        holder.summary.setText(weathers.get(position).getSummary());
        holder.highestTemp.setText("highest temperture is" + String.valueOf(weathers.get(position).getHighestTemperature()) + "\nLowenst temperture is "+String.valueOf(weathers.get(position).getLowestTemperature()));
        holder.icon.setText(weathers.get(position).getIcon());
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
