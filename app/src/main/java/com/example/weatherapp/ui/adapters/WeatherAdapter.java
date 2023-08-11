package com.example.weatherapp.ui.adapters;

import androidx.recyclerview.widget.DiffUtil;

import com.example.weatherapp.ui.adapters.adapter_delegates.HourlyInfoEveryDayAdapterDelegate;
import com.example.weatherapp.ui.adapters.adapter_delegates.MainInfoAdapterDelegate;
import com.example.weatherapp.ui.adapters.adapter_delegates.TextAdapterDelegate;
import com.example.weatherapp.ui.adapters.adapter_items.WeatherItem;
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends ListDelegationAdapter<List<WeatherItem>> {

    private final List<WeatherItem> weatherItems = new ArrayList<>();

    public void updateWeatherItems(List<WeatherItem> weatherItemsNew) {
        DiffUtil.DiffResult diffResult = DiffUtil
                .calculateDiff(new WeatherDiffUtilCallBack(weatherItems, weatherItemsNew));
        diffResult.dispatchUpdatesTo(this);
        weatherItems.clear();
        weatherItems.addAll(weatherItemsNew);
    }

    public WeatherAdapter(
            OnItemClickListener onItemClickListener
    ) {
        delegatesManager
                .addDelegate(new MainInfoAdapterDelegate(onItemClickListener))
                .addDelegate(new HourlyInfoEveryDayAdapterDelegate())
                .addDelegate(new TextAdapterDelegate());
        setItems(weatherItems);
    }
}