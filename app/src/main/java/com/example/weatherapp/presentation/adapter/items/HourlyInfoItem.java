package com.example.weatherapp.presentation.adapter.items;

public class HourlyInfoItem implements WeatherItem {

    private final String time;

    public HourlyInfoItem(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}