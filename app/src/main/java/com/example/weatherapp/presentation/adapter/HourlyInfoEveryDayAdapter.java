package com.example.weatherapp.presentation.adapter;

import com.example.weatherapp.presentation.adapter.items.WeatherItem;
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;

import java.util.List;

public class HourlyInfoEveryDayAdapter extends ListDelegationAdapter<List<WeatherItem>> {

    public HourlyInfoEveryDayAdapter(List<WeatherItem> weatherItems) {
        delegatesManager.addDelegate(new HourlyInfoAdapterDelegate());
        setItems(weatherItems);
    }
}