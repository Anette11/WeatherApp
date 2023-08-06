package com.example.weatherapp.presentation.adapter.items;

import androidx.annotation.DrawableRes;

public class MainInfoItem implements WeatherItem {

    private final String cityName;
    @DrawableRes
    private final int image;
    private final String temperature;
    private final String windSpeed;
    private final String humidity;
    private final String description;
    private final String time;

    public MainInfoItem(
            String cityName,
            int image,
            String temperature,
            String windSpeed,
            String humidity,
            String description,
            String time
    ) {
        this.cityName = cityName;
        this.image = image;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.description = description;
        this.time = time;
    }

    public String getCityName() {
        return cityName;
    }

    public int getImage() {
        return image;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }
}