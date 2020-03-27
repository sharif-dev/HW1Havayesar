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
    private OnNoteListener mOnNoteListener;


    public CityAdapter(List<ConvertjsonToCityController> city , OnNoteListener onNoteListener) {
        this.mOnNoteListener = onNoteListener;
        this.city = city;
    }

    @NonNull
    @Override
    public ViewHoldre onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.template_city , parent , false);
        return new ViewHoldre(view , mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldre holder, int position) {
        holder.name.setText(city.get(position).getPlaceName());
    }


    @Override
    public int getItemCount() {
        return city.size();
    }


    public  class ViewHoldre extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public OnNoteListener onNoteListener;

        public ViewHoldre( View itemView ,OnNoteListener onNoteListener) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}

