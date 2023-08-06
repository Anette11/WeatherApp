package com.example.weatherapp.presentation.adapter.items;

import java.util.List;

public class HourlyInfoEveryDayItem implements WeatherItem {

    private final List<WeatherItem> weatherItems;

    public HourlyInfoEveryDayItem(List<WeatherItem> weatherItems) {
        this.weatherItems = weatherItems;
    }

    public List<WeatherItem> getWeatherItems() {
        return weatherItems;
    }
}