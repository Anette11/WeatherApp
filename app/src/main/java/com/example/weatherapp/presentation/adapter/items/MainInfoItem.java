package com.example.weatherapp.presentation.adapter.items;

import androidx.annotation.DrawableRes;

public class MainInfoItem implements WeatherItem {

    private final String cityName;
    @DrawableRes
    private final int image;

    public MainInfoItem(
            String cityName,
            int image
    ) {
        this.cityName = cityName;
        this.image = image;
    }

    public String getCityName() {
        return cityName;
    }

    public int getImage() {
        return image;
    }
}