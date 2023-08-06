package com.example.weatherapp.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.databinding.WeatherHourlyInfoBinding;
import com.example.weatherapp.presentation.adapter.items.HourlyInfoItem;
import com.example.weatherapp.presentation.adapter.items.WeatherItem;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;

import java.util.List;

public class HourlyInfoAdapterDelegate extends AbsListItemAdapterDelegate<HourlyInfoItem, WeatherItem, HourlyInfoAdapterDelegate.HourlyInfoViewHolder> {

    @Override
    protected boolean isForViewType(
            @NonNull WeatherItem item,
            @NonNull List<WeatherItem> items,
            int position
    ) {
        return item instanceof HourlyInfoItem;
    }

    @NonNull
    @Override
    protected HourlyInfoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent
    ) {
        WeatherHourlyInfoBinding binding = WeatherHourlyInfoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new HourlyInfoViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull HourlyInfoItem item,
            @NonNull HourlyInfoViewHolder holder,
            @NonNull List<Object> payloads
    ) {
        holder.binding.textViewTime.setText(item.getTime());
        holder.binding.textViewTemperature.setText(item.getTemperature());
        holder.binding.textViewWindSpeed.setText(item.getWindSpeed());
        holder.binding.textViewHumidity.setText(item.getHumidity());
        holder.binding.textViewDescription.setText(item.getDescription());
        holder.binding.imageView.setBackgroundResource(item.getImage());
    }

    static class HourlyInfoViewHolder extends RecyclerView.ViewHolder {

        private final WeatherHourlyInfoBinding binding;

        public HourlyInfoViewHolder(WeatherHourlyInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}