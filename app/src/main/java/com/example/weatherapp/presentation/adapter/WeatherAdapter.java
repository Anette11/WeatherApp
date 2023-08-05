package com.example.weatherapp.presentation.adapter;

import android.app.Activity;

import com.example.weatherapp.presentation.adapter.items.WeatherItem;
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends ListDelegationAdapter<List<WeatherItem>> {

    private final List<WeatherItem> weatherItems = new ArrayList<>();

    public void updateWeatherItems(List<WeatherItem> newWeatherItems) {
        weatherItems.clear();
        weatherItems.addAll(newWeatherItems);
        notifyItemRangeInserted(0, newWeatherItems.size());
    }

    public WeatherAdapter(Activity activity) {
        delegatesManager
                .addDelegate(new MainInfoAdapterDelegate(activity))
                .addDelegate(new HourlyInfoAdapterDelegate(activity))
                .addDelegate(new TextAdapterDelegate(activity));
        setItems(weatherItems);
    }
}