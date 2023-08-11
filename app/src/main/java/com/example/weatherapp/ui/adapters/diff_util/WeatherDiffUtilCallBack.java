package com.example.weatherapp.ui.adapters.diff_util;

import androidx.recyclerview.widget.DiffUtil;

import com.example.weatherapp.ui.adapters.adapter_items.WeatherItem;

import java.util.List;

public class WeatherDiffUtilCallBack extends DiffUtil.Callback {

    private final List<WeatherItem> weatherItemsOld;
    private final List<WeatherItem> weatherItemsNew;

    public WeatherDiffUtilCallBack(
            List<WeatherItem> weatherItemsOld,
            List<WeatherItem> weatherItemsNew
    ) {
        this.weatherItemsOld = weatherItemsOld;
        this.weatherItemsNew = weatherItemsNew;
    }

    @Override
    public int getOldListSize() {
        return weatherItemsOld != null ? weatherItemsOld.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return weatherItemsNew != null ? weatherItemsNew.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(
            int oldItemPosition,
            int newItemPosition
    ) {
        return true;
    }

    @Override
    public boolean areContentsTheSame(
            int oldItemPosition,
            int newItemPosition
    ) {
        return weatherItemsNew.get(newItemPosition).compareTo(weatherItemsOld.get(oldItemPosition)) == 0;
    }
}