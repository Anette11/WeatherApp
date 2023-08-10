package com.example.weatherapp.presentation.adapter.items;

import java.util.HashSet;
import java.util.List;

public class HourlyInfoEveryDayItem extends WeatherItem {

    private final List<WeatherItem> weatherItems;

    public HourlyInfoEveryDayItem(List<WeatherItem> weatherItems) {
        this.weatherItems = weatherItems;
    }

    public List<WeatherItem> getWeatherItems() {
        return weatherItems;
    }

    @Override
    public int compareTo(WeatherItem weatherItem) {
        if (new HashSet<>(((HourlyInfoEveryDayItem) weatherItem).weatherItems).containsAll(this.weatherItems) &&
                ((HourlyInfoEveryDayItem) weatherItem).weatherItems.size() == this.weatherItems.size()
        ) {
            return 0;
        }
        return ((HourlyInfoEveryDayItem) weatherItem).weatherItems.size() < this.weatherItems.size() ? -1 : 1;
    }
}