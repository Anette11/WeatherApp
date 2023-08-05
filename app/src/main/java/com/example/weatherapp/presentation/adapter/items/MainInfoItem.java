package com.example.weatherapp.presentation.adapter.items;

import androidx.annotation.DrawableRes;

public class MainInfoItem implements WeatherItem {

    private final String cityName;
    @DrawableRes
    private final int image;
    private final Double temperature;
    private final Double windSpeed;
    private final Integer humidity;
    private final String description;

    public MainInfoItem(
            String cityName,
            int image,
            Double temperature,
            Double windSpeed,
            Integer humidity,
            String description
    ) {
        this.cityName = cityName;
        this.image = image;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public int getImage() {
        return image;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }
}