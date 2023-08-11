package com.example.weatherapp.ui.adapters.adapter_delegates;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.databinding.WeatherHourlyEveryDayBinding;
import com.example.weatherapp.ui.adapters.adapter_items.HourlyInfoEveryDayItem;
import com.example.weatherapp.ui.adapters.adapter_items.WeatherItem;
import com.example.weatherapp.ui.adapters.HourlyInfoEveryDayAdapter;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;

import java.util.List;

public class HourlyInfoEveryDayAdapterDelegate extends AbsListItemAdapterDelegate<HourlyInfoEveryDayItem, WeatherItem, HourlyInfoEveryDayAdapterDelegate.HourlyInfoEveryDayViewHolder> {

    @Override
    protected boolean isForViewType(
            @NonNull WeatherItem item,
            @NonNull List<WeatherItem> items,
            int position
    ) {
        return item instanceof HourlyInfoEveryDayItem;
    }

    @NonNull
    @Override
    protected HourlyInfoEveryDayViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent
    ) {
        WeatherHourlyEveryDayBinding binding = WeatherHourlyEveryDayBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new HourlyInfoEveryDayViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull HourlyInfoEveryDayItem item,
            @NonNull HourlyInfoEveryDayViewHolder holder,
            @NonNull List<Object> payloads
    ) {
        HourlyInfoEveryDayAdapter hourlyInfoEveryDayAdapter = new HourlyInfoEveryDayAdapter(item.getWeatherItems());
        holder.binding.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.binding.recyclerView.setAdapter(hourlyInfoEveryDayAdapter);
    }

    static class HourlyInfoEveryDayViewHolder extends RecyclerView.ViewHolder {

        private final WeatherHourlyEveryDayBinding binding;

        public HourlyInfoEveryDayViewHolder(WeatherHourlyEveryDayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}