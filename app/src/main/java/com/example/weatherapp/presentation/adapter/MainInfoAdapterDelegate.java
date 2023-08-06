package com.example.weatherapp.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.databinding.WeatherMainInfoBinding;
import com.example.weatherapp.presentation.adapter.items.MainInfoItem;
import com.example.weatherapp.presentation.adapter.items.WeatherItem;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;

import java.util.List;

public class MainInfoAdapterDelegate extends AbsListItemAdapterDelegate<MainInfoItem, WeatherItem, MainInfoAdapterDelegate.MainInfoViewHolder> {

    @Override
    protected boolean isForViewType(
            @NonNull WeatherItem item,
            @NonNull List<WeatherItem> items,
            int position
    ) {
        return item instanceof MainInfoItem;
    }

    @NonNull
    @Override
    protected MainInfoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent
    ) {
        WeatherMainInfoBinding binding = WeatherMainInfoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new MainInfoViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull MainInfoItem item,
            @NonNull MainInfoViewHolder holder,
            @NonNull List<Object> payloads
    ) {
        holder.binding.textViewCityName.setText(item.getCityName());
        holder.binding.textViewTemperature.setText(item.getTemperature() + " ℃");
        holder.binding.textViewWindSpeed.setText(item.getWindSpeed() + " km/h");
        holder.binding.textViewHumidity.setText(item.getHumidity() + " %");
        holder.binding.textViewDescription.setText(item.getDescription());
        holder.binding.textViewTime.setText(item.getTime());
        holder.binding.imageView.setBackgroundResource(item.getImage());
    }

    static class MainInfoViewHolder extends RecyclerView.ViewHolder {

        private final WeatherMainInfoBinding binding;

        public MainInfoViewHolder(WeatherMainInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}