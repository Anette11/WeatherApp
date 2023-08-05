package com.example.weatherapp.presentation.adapter.items;

import androidx.annotation.DrawableRes;

public class HourlyInfoItem implements WeatherItem {

    private final String time;
    @DrawableRes
    private final int image;
    private final Double temperature;
    private final Double windSpeed;
    private final Integer humidity;
    private final String description;

    public HourlyInfoItem(
            String time,
            int image,
            Double temperature,
            Double windSpeed,
            Integer humidity,
            String description
    ) {
        this.time = time;
        this.image = image;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.description = description;
    }

    public String getTime() {
        return time;
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