package com.example.weatherapp.ui.adapters;

import com.example.weatherapp.ui.adapters.adapter_delegates.HourlyInfoAdapterDelegate;
import com.example.weatherapp.ui.adapters.adapter_items.WeatherItem;
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;

import java.util.List;

public class HourlyInfoEveryDayAdapter extends ListDelegationAdapter<List<WeatherItem>> {

    public HourlyInfoEveryDayAdapter(List<WeatherItem> weatherItems) {
        delegatesManager.addDelegate(new HourlyInfoAdapterDelegate());
        setItems(weatherItems);
    }
}