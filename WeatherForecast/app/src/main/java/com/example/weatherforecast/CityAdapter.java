package com.example.weatherforecast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHoldre> {
    List<ConvertjsonToCityController> city;


    public CityAdapter(List<ConvertjsonToCityController> city) {
        this.city = city;
    }

    @NonNull
    @Override
    public ViewHoldre onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.template_city , parent , false);
        return new ViewHoldre(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldre holder, int position) {
        holder.name.setText(city.get(position).getPlaceName());
    }


    @Override
    public int getItemCount() {
        return city.size();
    }


    public  class ViewHoldre extends RecyclerView.ViewHolder{
        public TextView name;


        public ViewHoldre( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}

