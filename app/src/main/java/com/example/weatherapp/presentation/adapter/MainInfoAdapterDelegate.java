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

    private final OnItemClickListener onItemClickListener;

    public MainInfoAdapterDelegate(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

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
        holder.binding.textViewTemperature.setText(item.getTemperature());
        holder.binding.textViewWindSpeed.setText(item.getWindSpeed());
        holder.binding.textViewHumidity.setText(item.getHumidity());
        holder.binding.textViewDescription.setText(item.getDescription());
        holder.binding.imageView.setBackgroundResource(item.getImage());

        holder.binding.textViewCityName.setOnClickListener(v -> {
            int position = holder.getAbsoluteAdapterPosition();
            if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClick(item);
            }
        });
    }

    static class MainInfoViewHolder extends RecyclerView.ViewHolder {

        private final WeatherMainInfoBinding binding;

        public MainInfoViewHolder(WeatherMainInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}