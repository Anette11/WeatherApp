package com.example.weatherapp.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.databinding.WeatherHourlyInfoBinding;
import com.example.weatherapp.databinding.WeatherMainInfoBinding;
import com.example.weatherapp.presentation.adapter.items.HourlyInfoItem;
import com.example.weatherapp.presentation.adapter.items.MainInfoItem;
import com.example.weatherapp.presentation.adapter.items.WeatherItem;

import java.util.ArrayList;
import java.util.List;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<WeatherItem> weatherItems = new ArrayList<>();

    public void updateWeatherItems(List<WeatherItem> newWeatherItems) {
        weatherItems.clear();
        weatherItems.addAll(newWeatherItems);
        notifyItemRangeInserted(0, newWeatherItems.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        if (viewType == ItemViewType.MAIN_INFO.getType()) {
            WeatherMainInfoBinding binding = WeatherMainInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new MainInfoItemViewHolder(binding);
        } else if (viewType == ItemViewType.HOURLY_INFO.getType()) {
            WeatherHourlyInfoBinding binding = WeatherHourlyInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new HourlyInfoItemViewHolder(binding);
        } else {
            throw new IllegalArgumentException("ItemViewType is not known in method onCreateViewHolder");
        }
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder,
            int position
    ) {
        WeatherItem weatherItem = weatherItems.get(position);
        if (holder.getItemViewType() == ItemViewType.MAIN_INFO.getType()) {
            ((MainInfoItemViewHolder) holder).binding.textViewCityName.setText("");
        } else if (holder.getItemViewType() == ItemViewType.HOURLY_INFO.getType()) {
            ((HourlyInfoItemViewHolder) holder).binding.textView.setText("");
        } else {
            throw new IllegalArgumentException("ItemViewType is not known in method onBindViewHolder");
        }
    }

    @Override
    public int getItemCount() {
        return weatherItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        WeatherItem weatherItem = weatherItems.get(position);
        if (weatherItem instanceof MainInfoItem) {
            return ItemViewType.MAIN_INFO.getType();
        } else if (weatherItem instanceof HourlyInfoItem) {
            return ItemViewType.HOURLY_INFO.getType();
        } else {
            throw new IllegalArgumentException("ItemViewType is not known in method getItemViewType");
        }
    }

    private static class MainInfoItemViewHolder extends RecyclerView.ViewHolder {

        private final WeatherMainInfoBinding binding;

        public MainInfoItemViewHolder(WeatherMainInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private static class HourlyInfoItemViewHolder extends RecyclerView.ViewHolder {
        private final WeatherHourlyInfoBinding binding;

        public HourlyInfoItemViewHolder(WeatherHourlyInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}