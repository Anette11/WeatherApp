package com.example.weatherapp.ui.adapters.adapter_items;

import androidx.annotation.DrawableRes;

public class HourlyInfoItem extends WeatherItem {

    private final String time;
    @DrawableRes
    private final int image;
    private final String temperature;
    private final String windSpeed;
    private final String humidity;
    private final String description;

    public HourlyInfoItem(
            String time,
            int image,
            String temperature,
            String windSpeed,
            String humidity,
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

    @Override
    public int compareTo(WeatherItem weatherItem) {
        return ((HourlyInfoItem) weatherItem).time.compareTo(this.time);
    }
}