package com.example.weatherapp.presentation.adapter;

import com.example.weatherapp.presentation.adapter.items.WeatherItem;
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends ListDelegationAdapter<List<WeatherItem>> {

    private final List<WeatherItem> weatherItems = new ArrayList<>();

    public void updateWeatherItems(List<WeatherItem> newWeatherItems) {
        if (newWeatherItems == null) return;
        weatherItems.clear();
        weatherItems.addAll(newWeatherItems);
        notifyDataSetChanged();
    }

    public WeatherAdapter(
            OnItemClickListener onItemClickListener
    ) {
        delegatesManager
                .addDelegate(new MainInfoAdapterDelegate(onItemClickListener))
                .addDelegate(new HourlyInfoEveryDayDelegateAdapter())
                .addDelegate(new TextAdapterDelegate());
        setItems(weatherItems);
    }
}